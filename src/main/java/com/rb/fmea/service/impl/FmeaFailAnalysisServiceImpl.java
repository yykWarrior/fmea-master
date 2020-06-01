package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaFailAnalysisMapper;
import com.rb.fmea.dao.FmeaFailAnalysisRelateMapper;
import com.rb.fmea.dao.FmeaFunctionMapper;
import com.rb.fmea.dao.FmeaStructureMapper;
import com.rb.fmea.dto.FmeaFailAnalysisDto;
import com.rb.fmea.dto.FmeaFunctionDto;
import com.rb.fmea.dto.FmeaStructureDto;
import com.rb.fmea.entities.FmeaFailAnalysis;
import com.rb.fmea.entities.FmeaFailAnalysisRelate;
import com.rb.fmea.entities.FmeaFunction;
import com.rb.fmea.entities.FmeaStructure;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaFailAnalysisService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version v1.0
 * @ClassName: FmeaFailAnalysisServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/29 11:30
 */
@Service
public class FmeaFailAnalysisServiceImpl implements FmeaFailAnalysisService {
    @Resource
    private FmeaFailAnalysisMapper fmeaFailAnalysisMapper;
    @Resource
    private FmeaFailAnalysisRelateMapper fmeaFailAnalysisRelateMapper;
    @Resource
    private FmeaFunctionMapper fmeaFunctionMapper;
    @Resource
    private FmeaStructureMapper fmeaStructureMapper;
    /**
     * @Author yyk
     * @Description //TODO 删除失效分析
     * @Date 2020/5/29 11:31
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            String[] idArray = StringProcess.spliteString(ids, ",");
            for(String id:idArray){
                fmeaFailAnalysisMapper.deleteByPrimaryKey(Integer.parseInt(id));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 修改失效分析
     * @Date 2020/5/29 13:13
     * @Param [fmeaFailAnalysis]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaFailAnalysis fmeaFailAnalysis) {
        try {
            fmeaFailAnalysisMapper.updateByPrimaryKey(fmeaFailAnalysis);
            //生成履历信息

            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 失效分析之间建立关系
     * @Date 2020/5/29 13:21
     * @Param [parentFailAnalysisId, nextFailAnalysisIds]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result setFmeaFailAnalysisRelate(int parentFailAnalysisId, String nextFailAnalysisIds) {
        try {
            String[] nextFailAnalysisIdArray = StringProcess.spliteString(nextFailAnalysisIds, ",");
            for (String nextFailAnalysisId:nextFailAnalysisIdArray){
                //判断关系是否已经建立
                FmeaFailAnalysisRelate fmeaFailAnalysisRelate=fmeaFailAnalysisRelateMapper.selectByfailAnalysisSuperiorIdAndFailAnalysisNextId(parentFailAnalysisId,Integer.parseInt(nextFailAnalysisId));
                if(fmeaFailAnalysisRelate==null){
                    FmeaFailAnalysisRelate fmeaFailAnalysisRelate1 = new FmeaFailAnalysisRelate();
                    fmeaFailAnalysisRelate1.setFailAnalysisSuperiorId(parentFailAnalysisId);
                    fmeaFailAnalysisRelate1.setFailAnalysisNextId(Integer.parseInt(nextFailAnalysisId));
                    fmeaFailAnalysisRelateMapper.insert(fmeaFailAnalysisRelate1);
                }
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 在一个功能下添加多个功能分析
     * @Date 2020/5/29 14:27
     * @Param [functionId, fmeaFailAnalysis]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(int functionId, String fmeaFailAnalysisDesc) {
        try {
            String[] descArray=StringProcess.spliteString(fmeaFailAnalysisDesc,",");
            for(String desc:descArray){
                FmeaFailAnalysis fmeaFailAnalysis=new FmeaFailAnalysis();
                fmeaFailAnalysis.setAnalysisStandard(desc);
                fmeaFailAnalysis.setFunctionId(functionId);
                fmeaFailAnalysisMapper.insert(fmeaFailAnalysis);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 查询当前功能的上级功能和下级功能对应的失效分析
     * @Date 2020/5/29 17:06
     * @Param [functionId]
     * @return com.rb.fmea.result.Result
     **/
    /**
     * {
     *   "message": "成功",
     *   "data": [
     *     {
     *       "fmeaStructure": {
     *         "id": 11,
     *         "structureName": "零件1",
     *         "structureDesc": "零件1描述",
     *         "superiorId": 10,
     *         "fmeaId": 2
     *       },
     *       "fmeaFunction": {
     *         "id": 3,
     *         "functionStandard": "4",
     *         "functionRequire": "要求3",
     *         "fmeaStructureId": 11
     *       },
     *       "fmeaFailAnalysis": {
     *         "id": 3,
     *         "analysisStandard": "失效3",
     *         "fmeaFailAnalysisDesc": "失效3描述",
     *         "functionId": 3,
     *         "severity": "0"
     *       },
     *       "parentFmeaStructureDtoList": [
     *         {
     *           "id": null,
     *           "structureName": "总成1",
     *           "structureDesc": "总成1描述",
     *           "fmeaId": null,
     *           "fmeaStructureDtoList": null,
     *           "fmeaFunctionDtoList": null,
     *           "fmeaFunctionList": [
     *             {
     *               "id": null,
     *               "functionDesc": "3",
     *               "functionRequire": "要求2",
     *               "fmeaStructureId": 10,
     *               "fmeaFailAnalysisList": [
     *                 {
     *                   "id": 2,
     *                   "analysisStandard": "失效2",
     *                   "fmeaFailAnalysisDesc": "失效2描述",
     *                   "functionId": 2,
     *                   "severity": null
     *                 }
     *               ]
     *             }
     *           ]
     *         }
     *       ],
     *       "nextFmeaStructureDtoList": [
     *         {
     *           "id": null,
     *           "structureName": "特性3",
     *           "structureDesc": "特性3描述",
     *           "fmeaId": null,
     *           "fmeaStructureDtoList": null,
     *           "fmeaFunctionDtoList": null,
     *           "fmeaFunctionList": [
     *             {
     *               "id": null,
     *               "functionDesc": "2",
     *               "functionRequire": "要求4",
     *               "fmeaStructureId": 15,
     *               "fmeaFailAnalysisList": [
     *                 {
     *                   "id": 4,
     *                   "analysisStandard": "失效4",
     *                   "fmeaFailAnalysisDesc": "失效4描述",
     *                   "functionId": 4,
     *                   "severity": null
     *                 }
     *               ]
     *             }
     *           ]
     *         }
     *       ]
     *     },
     *     {
     *       "fmeaStructure": {
     *         "id": 11,
     *         "structureName": "零件1",
     *         "structureDesc": "零件1描述",
     *         "superiorId": 10,
     *         "fmeaId": 2
     *       },
     *       "fmeaFunction": {
     *         "id": 8,
     *         "functionStandard": "4",
     *         "functionRequire": "要求9",
     *         "fmeaStructureId": 11
     *       },
     *       "fmeaFailAnalysis": {
     *         "id": 8,
     *         "analysisStandard": "失效8",
     *         "fmeaFailAnalysisDesc": "失效8描述",
     *         "functionId": 8,
     *         "severity": "0"
     *       },
     *       "parentFmeaStructureDtoList": [],
     *       "nextFmeaStructureDtoList": []
     *     }
     *   ],
     *   "retCode": 200
     * }
     **/
    @Override
    public Result selectFmeaFailAnalysisRelate(int structureId) {

        List<FmeaFailAnalysisDto> fmeaFailAnalysisDtoList=new ArrayList<>();
        //根据结构查询出当前结构下所有的失效分析
        List<FmeaFailAnalysis> fmeaFailAnalysisList=fmeaFailAnalysisMapper.selectFailAnalysisByStructId(structureId);
        //根据失效分析查询
        for(FmeaFailAnalysis fmeaFailAnalysis:fmeaFailAnalysisList){
            FmeaFailAnalysisDto fmeaFailAnalysisDto = new FmeaFailAnalysisDto();
            //查询上级失效模式
            List<FmeaFailAnalysis> superiorFmeaFailAnalysisList=fmeaFailAnalysisRelateMapper.selectByFailAnalysisNextId(fmeaFailAnalysis.getId());
            //查询下级失效模式
            List<FmeaFailAnalysis> nextFmeaFailAnalysisList=fmeaFailAnalysisRelateMapper.selectByfailAnalysisSuperiorId(fmeaFailAnalysis.getId());
            //上级失效模式以功能分组
            List<FmeaFunctionDto> superiorFmeaFunctionDtoList = getGroupByFunctionId(superiorFmeaFailAnalysisList);
            //下级失效模式以功能分组
            List<FmeaFunctionDto> nextFmeaFunctionDtoList = getGroupByFunctionId(nextFmeaFailAnalysisList);
            //上级结构分组
            List<FmeaStructureDto> superiorFmeaStructureDto = getGroupByStructId(superiorFmeaFunctionDtoList);
            //下级结构分组
            List<FmeaStructureDto> nextFmeaStructureDto = getGroupByStructId(nextFmeaFunctionDtoList);
            fmeaFailAnalysisDto.setNextFmeaStructureDtoList(nextFmeaStructureDto);
            fmeaFailAnalysisDto.setParentFmeaStructureDtoList(superiorFmeaStructureDto);
            //查询当前失效分析的功能和结构
            fmeaFailAnalysisDto.setFmeaFailAnalysis(fmeaFailAnalysis);
            FmeaFunction fmeaFunction = fmeaFunctionMapper.selectByPrimaryKey(fmeaFailAnalysis.getFunctionId());
            FmeaStructure fmeaStructure = fmeaStructureMapper.selectByPrimaryKey(fmeaFunction==null?0:fmeaFunction.getFmeaStructureId());
            if(fmeaStructure!=null){
                fmeaFailAnalysisDto.setFmeaStructure(fmeaStructure);
                fmeaFailAnalysisDto.setFmeaFunction(fmeaFunction);
            }
            fmeaFailAnalysisDtoList.add(fmeaFailAnalysisDto);
        }
        return Result.success(fmeaFailAnalysisDtoList);
    }

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的所有失效分析
     * @Date 2020/6/1 15:39
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectOneStructure(int structureId) {
        try {
            List<FmeaFailAnalysis> fmeaFailAnalysisList=fmeaFailAnalysisMapper.selectOneStructure(structureId);
            return Result.success(fmeaFailAnalysisList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 查询当前失效分析对应功能的的下级关联功能的所有失效分析
     * @Date 2020/6/1 15:53
     * @Param [fmeaFailAnalysisId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectNextFunction(int fmeaFailAnalysisId) {
        try {
            List<FmeaFailAnalysis> fmeaFailAnalysisList=fmeaFailAnalysisMapper.selectNextFunction(fmeaFailAnalysisId);
            return Result.success(fmeaFailAnalysisList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }


    /**
     * @Author yyk
     * @Description //TODO 以功能id对失效分析分组，封装
     * @Date 2020/6/1 13:14
     * @Param []
     * @return java.util.List<com.rb.fmea.dto.FmeaFunctionDto>
     **/
    public List<FmeaFunctionDto> getGroupByFunctionId(List<FmeaFailAnalysis> fmeaFailAnalysisList){
        Map<Integer, List<FmeaFailAnalysis>> structureMap = fmeaFailAnalysisList.stream().collect(Collectors.groupingBy(FmeaFailAnalysis::getFunctionId));
        Iterator<Map.Entry<Integer, List<FmeaFailAnalysis>>> iterator = structureMap.entrySet().iterator();
        List<FmeaFunctionDto> fmeaFunctionDtoList=new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<Integer, List<FmeaFailAnalysis>> next = iterator.next();
            //获取key，即功能id
            Integer functionId = next.getKey();
            //获取value，即功能下对应的失效分析id
            List<FmeaFailAnalysis> fmeaFailAnalysisList1 = next.getValue();
            FmeaFunctionDto fmeaFunctionDto=new FmeaFunctionDto();
            if(functionId!=null&&functionId!=0&&fmeaFailAnalysisList1.size()!=0){
                FmeaFunction fmeaFunction = fmeaFunctionMapper.selectByPrimaryKey(functionId);
                fmeaFunctionDto.setId(fmeaFunction.getId());
                fmeaFunctionDto.setFunctionDesc(fmeaFunction.getFunctionStandard());
                fmeaFunctionDto.setFunctionRequire(fmeaFunction.getFunctionRequire());
                fmeaFunctionDto.setFmeaStructureId(fmeaFunction.getFmeaStructureId());
                fmeaFunctionDto.setFmeaFailAnalysisList(fmeaFailAnalysisList1);
            }
            fmeaFunctionDtoList.add(fmeaFunctionDto);
        }
        return fmeaFunctionDtoList;
    }



    /**
     * @Author yyk
     * @Description //TODO 以结构id对功能分组，封装
     * @Date 2020/6/1 13:14
     * @Param []
     * @return java.util.List<com.rb.fmea.dto.FmeaFunctionDto>
     **/
    public List<FmeaStructureDto> getGroupByStructId(List<FmeaFunctionDto> fmeaFunctionDtoList){
        Map<Integer, List<FmeaFunctionDto>> structureMap = fmeaFunctionDtoList.stream().collect(Collectors.groupingBy(FmeaFunctionDto::getFmeaStructureId));
        Iterator<Map.Entry<Integer, List<FmeaFunctionDto>>> iterator = structureMap.entrySet().iterator();
        List<FmeaStructureDto> fmeaStructureDtoList=new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<Integer, List<FmeaFunctionDto>> next = iterator.next();
            //获取key，即结构id
            Integer structureId = next.getKey();
            //获取value，即结构下对应的功能
            List<FmeaFunctionDto> fmeaFunctionDtoList1 = next.getValue();
            FmeaStructureDto fmeaStructureDto=new FmeaStructureDto();
            if(structureId!=null&&structureId!=0&&fmeaFunctionDtoList1.size()!=0){
                FmeaStructure fmeaStructure = fmeaStructureMapper.selectByPrimaryKey(structureId);
                fmeaStructureDto.setStructureName(fmeaStructure.getStructureName());
                fmeaStructureDto.setStructureDesc(fmeaStructure.getStructureDesc());
                fmeaStructureDto.setId(fmeaStructure.getId());
                fmeaStructureDto.setFmeaFunctionList(fmeaFunctionDtoList1);
            }
            fmeaStructureDtoList.add(fmeaStructureDto);
        }
        return fmeaStructureDtoList;
    }
}
