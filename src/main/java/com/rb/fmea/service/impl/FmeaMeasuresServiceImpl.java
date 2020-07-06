package com.rb.fmea.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rb.fmea.dao.*;
import com.rb.fmea.dto.OptMeasureDto;
import com.rb.fmea.dto.OptRiskDto;
import com.rb.fmea.dto.RiskDto;
import com.rb.fmea.entities.*;
import com.rb.fmea.file.UploadFile;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaApService;
import com.rb.fmea.service.FmeaMeasuresService;
import com.rb.fmea.service.FmeaService;
import com.rb.fmea.util.DateUtil;
import com.rb.fmea.util.ObjectUtil;
import com.rb.fmea.util.StringProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @version v1.0
 * @ClassName: FmeaMeasuresServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/2 9:41
 */
@Transactional
@Service
public class FmeaMeasuresServiceImpl implements FmeaMeasuresService {
    @Resource
    private FmeaMeasuresMapper fmeaMeasuresMapper;
    @Resource
    private FmeaApMapper fmeaApMapper;
    @Resource
    private FmeaFailAnalysisMapper fmeaFailAnalysisMapper;
    @Resource
    private FmeaStructureMapper fmeaStructureMapper;
    private final static String PATH="measure";
    @Autowired
    private FmeaApService fmeaApService;
    @Autowired
    private FmeaService fmeaService;
    @Resource
    private FmeaResumeMapper fmeaResumeMapper;
    /**
     * @Author yyk
     * @Description //TODO 批量插入一个失效分析的措施
     * @Date 2020/6/2 9:42
     * @Param [measures]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public synchronized Result insert(String measures, int failAnalysisId, int category, int optimize) {
        try {
            String apValue="";
            JSONArray jsonArray = JSONArray.parseArray(measures);
            List<FmeaMeasures> fmeaMeasuresList = JSONObject.parseArray(jsonArray.toJSONString(), FmeaMeasures.class);
            for(FmeaMeasures fmeaMeasures:fmeaMeasuresList){
                String fmeaMeasuresStandard = fmeaMeasures.getFmeaMeasuresStandard();
                String fmeaMeasuresDesc = fmeaMeasures.getFmeaMeasuresDesc();
                if((fmeaMeasuresStandard!=null&&!"".equals(fmeaMeasuresStandard))||(fmeaMeasuresDesc!=null&&!"".equals(fmeaMeasuresDesc))) {
                    fmeaMeasures.setFmeaFailAnalysisId(failAnalysisId);
                    fmeaMeasures.setFmeaMeasuresCategory(category);
                    fmeaMeasures.setOptimizeMeasures(optimize);
                    if (fmeaMeasures.getFrequencyDegree() == null)
                        fmeaMeasures.setFrequencyDegree(0);
                    //返回ap值和更新ap值
                    apValue = fmeaApService.updateAp(failAnalysisId, fmeaMeasures);
                    fmeaMeasuresMapper.insert(fmeaMeasures);
                }
            }
            return Result.success(apValue);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 删除措施
     * @Date 2020/6/2 10:52
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids, int fmeaId) {
        String apValue="";
        try {
            String s = StringProcess.determinesWhetherStringEndsInComma(ids);
            String[] idArray = StringProcess.spliteString(s, ",");
            for(String id:idArray){
                //根据id查询措施信息
                FmeaMeasures fmeaMeasures = fmeaMeasuresMapper.selectByPrimaryKey(Integer.parseInt(id));

                //查询fmea信息，返回fmea状态
                Boolean aBoolean = fmeaService.selectByIdReturnState(fmeaId);
                if(aBoolean) {
                    FmeaResume fmeaResume=new FmeaResume();
                    //查询出对应的结构
                    FmeaStructure fmeaStructure = fmeaStructureMapper.selectByMeasureId(Integer.parseInt(id));
                    Integer fmeaMeasuresCategory = fmeaMeasures.getFmeaMeasuresCategory();
                    Integer optimizeMeasures = fmeaMeasures.getOptimizeMeasures();
                    //0是风险，1是优化
                    String category = "";
                    String opt = "";
                    if (fmeaMeasuresCategory == 0) {
                        category = "风险";
                        opt = judgeMeasure(optimizeMeasures);
                    } else if (fmeaMeasuresCategory == 1) {
                        category = "优化";
                        opt = judgeMeasure(optimizeMeasures);
                    }
                    String old=opt+"描述:"+fmeaMeasures.getFmeaMeasuresDesc()+";"+opt+"标准"+fmeaMeasures.getFmeaMeasuresStandard();
                    fmeaResume.setAfterChange("").setBeforeChange(old).setCreateDate(DateUtil.parseTime(new Date())).setStructureName(fmeaStructure == null ? "" : fmeaStructure.getStructureName()).setStep(category).setFmeaId(fmeaId);
                    fmeaResumeMapper.insert(fmeaResume);
                }
                fmeaMeasuresMapper.deleteByPrimaryKey(Integer.parseInt(id));
                //更新ap值
                apValue = fmeaApService.updateAp(fmeaMeasures.getFmeaFailAnalysisId(), fmeaMeasures);
            }
            return Result.success(apValue);
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }


    public String judgeMeasure(int opt){
        if(opt==0){
            return "预防措施";
        }else if(opt==1){
            return "探测措施";
        }else{
            return "";
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改措施
     * @Date 2020/6/2 11:02
     * @Param [fmeaMeasures]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaMeasures fmeaMeasures, int fmeaId) {
        try {
            String apValue="";
            //查询fmea信息，返回fmea状态
            Boolean aBoolean = fmeaService.selectByIdReturnState(fmeaId);
            if(aBoolean) {
                FmeaResume fmeaResume=new FmeaResume();
                StringBuilder oldString = new StringBuilder();
                StringBuilder newString = new StringBuilder();
                //查询出对应的结构
                FmeaStructure fmeaStructure = fmeaStructureMapper.selectByMeasureId(fmeaMeasures.getId());
                //查询原来的数据
                FmeaMeasures fmeaMeasures1 = fmeaMeasuresMapper.selectByPrimaryKey(fmeaMeasures.getId());
                //生成履历
                Map<String, Object[]> compare = ObjectUtil.compare(fmeaMeasures1, fmeaMeasures);
                Integer fmeaMeasuresCategory = fmeaMeasures.getFmeaMeasuresCategory();
                Integer optimizeMeasures = fmeaMeasures.getOptimizeMeasures();
                //0是风险，1是优化
                String category = "";
                String opt = "";
                if (fmeaMeasuresCategory == 0) {
                    category = "风险";
                    opt = judgeMeasure(optimizeMeasures);
                } else if (fmeaMeasuresCategory == 1) {
                    category = "优化";
                    opt = judgeMeasure(optimizeMeasures);
                }
                Iterator<Map.Entry<String, Object[]>> iterator = compare.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object[]> next = iterator.next();
                    String key = next.getKey();
                    String filedName = fmeaMeasures.getName(key,fmeaMeasuresCategory);
                    Object[] value = next.getValue();
                    //旧值
                    oldString.append(opt).append(filedName).append(":").append(value[0]).append(";");
                    //新值
                    newString.append(opt).append(filedName).append(":").append(value[1]).append(";");
                }
                fmeaResume.setAfterChange(newString.toString()).setBeforeChange(oldString.toString()).setCreateDate(DateUtil.parseTime(new Date())).setStructureName(fmeaStructure == null ? "" : fmeaStructure.getStructureName()).setStep(category).setFmeaId(fmeaId);
                if(!("".equals(oldString.toString())&&"".equals(newString.toString()))) {
                    fmeaResumeMapper.insert(fmeaResume);
                }
            }
            fmeaMeasuresMapper.updateByPrimaryKey(fmeaMeasures);
            //返回ap值和更新ap值
            apValue = fmeaApService.updateAp(fmeaMeasures.getFmeaFailAnalysisId(), fmeaMeasures);
            return Result.success(apValue);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据是否优化，和措施类别查询
     * @Date 2020/6/5 13:58
     * @Param [fmeaMeasuresCategory, optimizeMeasures]
     * @return java.util.List<com.rb.fmea.entities.FmeaMeasures>
     **/
    @Override
    public List<FmeaMeasures> selectFmeaMeasuresCategoryAndOptimizeMeasures(int fmeaMeasuresCategory, int optimizeMeasures) {
        List<FmeaMeasures> fmeaMeasuresList= fmeaMeasuresMapper.selectFmeaMeasuresCategoryAndOptimizeMeasures(fmeaMeasuresCategory,optimizeMeasures);
        return fmeaMeasuresList;
    }

    /**
     * @Author yyk
     * @Description //TODO 根据结构id查询对应功能下的失效分析下的措施
     * @Date 2020/6/9 13:30
     * @Param [structureId]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto selectByStructureId(int structureId, int page, int limit) {
        page=(page-1)*limit;
        List<RiskDto> riskDtoList=new ArrayList<>();
        //查询当前结构下的所有措施
        List<FmeaMeasures> fmeaMeasuresList=fmeaMeasuresMapper.selectByStructureId(structureId,page,limit,0);
        //查询总条数
        int count=fmeaMeasuresMapper.count(structureId,0);
        for(FmeaMeasures fmeaMeasures:fmeaMeasuresList){
            RiskDto riskDto = new RiskDto();
            //0是预防措施，1是探测措施
            Integer fmeaMeasuresCategory = fmeaMeasures.getFmeaMeasuresCategory();
            if(fmeaMeasuresCategory==0){
                riskDto.setFrequencyDegree(fmeaMeasures.getFrequencyDegree());
                riskDto.setFmeaMeasureCategory("预防措施");
            }else if(fmeaMeasuresCategory==1){
                riskDto.setDetectionDegree(fmeaMeasures.getFrequencyDegree());
                riskDto.setFmeaMeasureCategory("探测措施");
            }
            //0是优化，1是不优化
            //Integer optimizeMeasures = fmeaMeasures.getOptimizeMeasures();
            //查询对应的严重度和ap值
            FmeaAp fmeaAp = fmeaApMapper.selectFailAnalysisId(fmeaMeasures.getFmeaFailAnalysisId());
            if(fmeaAp!=null){
                riskDto.setApValue(fmeaAp.getApValue());
                riskDto.setSeverity(fmeaAp.getSeverity());
            }
            //查询失效模式
            FmeaFailAnalysis fmeaFailAnalysis = fmeaFailAnalysisMapper.selectByPrimaryKey(fmeaMeasures.getFmeaFailAnalysisId());
            if(fmeaFailAnalysis!=null){
                riskDto.setFailAnalysis(fmeaFailAnalysis.getAnalysisStandard());
            }
            riskDto.setFmeaMeasuresDesc(fmeaMeasures.getFmeaMeasuresDesc());
            riskDtoList.add(riskDto);
        }
        return new ResultDto(0,"",count,riskDtoList);
    }


    /**
     * @Author yyk
     * @Description //TODO 根据结构id查询对应功能下的失效分析下的优化措施
     * @Date 2020/6/10 9:50
     * @Param [structureId, page, limit]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto selectByStructureIdAndOpt(int structureId, int page, int limit) {
        page=(page-1)*limit;
        List<OptRiskDto> optRiskDtoList=new ArrayList<>();
        //查询当前结构下的所有措施
        List<FmeaMeasures> fmeaMeasuresList=fmeaMeasuresMapper.selectByStructureId(structureId,page,limit,1);
        //查询总条数
        int count=fmeaMeasuresMapper.count(structureId,1);
        for(FmeaMeasures fmeaMeasures:fmeaMeasuresList){
            OptRiskDto optRiskDto = new OptRiskDto();
            //0是预防措施，1是探测措施
            Integer fmeaMeasuresCategory = fmeaMeasures.getFmeaMeasuresCategory();
            if(fmeaMeasuresCategory==0){
                optRiskDto.setFrequencyDegree(fmeaMeasures.getFrequencyDegree());
                optRiskDto.setFmeaMeasureCategory("预防措施");
            }else if(fmeaMeasuresCategory==1){
                optRiskDto.setDetectionDegree(fmeaMeasures.getFrequencyDegree());
                optRiskDto.setFmeaMeasureCategory("探测措施");
            }
            optRiskDto.setFinshDate(fmeaMeasures.getFinshDate());
            optRiskDto.setChargeName(fmeaMeasures.getChargeName());
            optRiskDto.setFinshTime(fmeaMeasures.getFinshTime());
            optRiskDto.setState(fmeaMeasures.getState());
            optRiskDto.setTakeMeasures(fmeaMeasures.getTakeMeasures());
            //0是优化，1是不优化
            //Integer optimizeMeasures = fmeaMeasures.getOptimizeMeasures();
            //查询对应的严重度和ap值
            FmeaAp fmeaAp = fmeaApMapper.selectFailAnalysisId(fmeaMeasures.getFmeaFailAnalysisId());
            if(fmeaAp!=null){
                optRiskDto.setApValue(fmeaAp.getApValue());
                optRiskDto.setSeverity(fmeaAp.getSeverity());
            }
            //查询失效模式
            FmeaFailAnalysis fmeaFailAnalysis = fmeaFailAnalysisMapper.selectByPrimaryKey(fmeaMeasures.getFmeaFailAnalysisId());
            if(fmeaFailAnalysis!=null){
                optRiskDto.setFailAnalysis(fmeaFailAnalysis.getAnalysisStandard());
            }
            optRiskDto.setFmeaMeasuresDesc(fmeaMeasures.getFmeaMeasuresDesc());
            optRiskDtoList.add(optRiskDto);
        }
        return new ResultDto(0,"",count,optRiskDtoList);
    }

    /**
     * @Author yyk
     * @Description //TODO 一个产品下的优化措施监控
     * @Date 2020/6/19 16:46
     * @Param [productId, pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto selectOptMeasureOneProduct(int productId, PageParameter pageParameter) {
        try {
            int page=(pageParameter.getPage()-1);
            List<OptMeasureDto> optMeasureDtoList=fmeaMeasuresMapper.selectOptMeasureOneProduct(productId,page,pageParameter.getLimit());
            //查询总条数
            int count=fmeaMeasuresMapper.countOneProduct(productId);
            return new ResultDto(0,"",count,optMeasureDtoList);
        } catch (Exception e) {
            return new ResultDto(1,"");
        }
    }

    /**
     * @Author yyk
     * @Description //TODO修改不执行状态
     * @Date 2020/6/20 9:12
     * @Param [measureId]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public Result updateState(int measureId) {
        try {
            fmeaMeasuresMapper.updateState(measureId);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 点击完成，改变状态
     * @Date 2020/6/20 10:34
     * @Param [multipartFile]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result updateStateFinsh(MultipartFile multipartFile, int measureId) {
        //上传文件
        try {
            String newName = UploadFile.oneUploadFile(multipartFile, PATH);
            //查询出对应的措施
            FmeaMeasures fmeaMeasures = fmeaMeasuresMapper.selectByPrimaryKey(measureId);
            fmeaMeasures.setTakeMeasures(PATH+"/"+newName);
            fmeaMeasures.setState(1);
            fmeaMeasures.setFinshTime(DateUtil.parseTime(new Date()));
            fmeaMeasuresMapper.updateByPrimaryKey(fmeaMeasures);
            return Result.success();
        } catch (IOException e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }


}
