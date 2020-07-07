package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaResumeMapper;
import com.rb.fmea.dao.FmeaStructureMapper;
import com.rb.fmea.dto.CommParam;
import com.rb.fmea.dto.FmeaStructureDto;
import com.rb.fmea.entities.FmeaResume;
import com.rb.fmea.entities.FmeaStructure;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaResumeService;
import com.rb.fmea.service.FmeaStructureService;
import com.rb.fmea.util.DateUtil;
import com.rb.fmea.util.StringProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaStructureServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 15:48
 */
@Service
public class FmeaStructureServiceImpl implements FmeaStructureService {
    @Resource
    private FmeaStructureMapper fmeaStructureMapper;
    @Resource
    private FmeaResumeMapper fmeaResumeMapper;
    @Autowired
    private FmeaResumeService fmeaResumeService;
    /**
     * @Author yyk
     * @Description //TODO 插入客户和总成
     * @Date 2020/5/21 15:49
     * @Param [fmeaId, customer, assembly]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insertCustomerAndAssembly(int fmeaId, String customer, String assembly,String customerDesc,String assemblyDesc) {
        try {
            //插入客户信息
            FmeaStructure fmeaStructure=new FmeaStructure(customer,customerDesc,0,fmeaId);
            fmeaStructureMapper.insert(fmeaStructure);
            int customerId =fmeaStructure.getId();
                    //插入总成信息
            FmeaStructure fmeaStructure1=new FmeaStructure(assembly,assemblyDesc,customerId,fmeaId);
            fmeaStructureMapper.insert(fmeaStructure1);
            return Result.success(fmeaStructure1.getId());
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 在总成下插入零件
     * @Date 2020/5/21 16:13
     * @Param [assemblyId, parts]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insertParts(int assemblyId, String parts) {
        try {
            FmeaStructure fmeaStructure1=fmeaStructureMapper.selectBySuperiorId(assemblyId);
            int fmeaId = fmeaStructure1 == null ? 0 : fmeaStructure1.getFmeaId();
            String[] partArray = StringProcess.spliteString(parts, ",");
            for(int i=0;i<partArray.length;i++) {
                FmeaStructure fmeaStructure = new FmeaStructure(partArray[i],null,assemblyId,fmeaId);
                fmeaStructureMapper.insert(fmeaStructure);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaId查询
     * @Date 2020/5/21 16:57
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByFmeaId(int fmeaId) {
        try {
            List<FmeaStructureDto> fmeaStructureDtoList= fmeaStructureMapper.selectBySuperiorIdAndFmeaId(0,fmeaId);
            fmeaStructureDtoList = setState(fmeaStructureDtoList, 0);
            for(FmeaStructureDto fmeaStructureDto:fmeaStructureDtoList){
                List<FmeaStructureDto> fmeaStructureDtoList1 = fmeaStructureMapper.selectBySuperiorIdAndFmeaId(fmeaStructureDto.getId(), fmeaId);
                fmeaStructureDtoList1=setState(fmeaStructureDtoList1, 1);
                for(FmeaStructureDto fmeaStructureDto1:fmeaStructureDtoList1){
                    List<FmeaStructureDto> fmeaStructureDtoList2 = fmeaStructureMapper.selectBySuperiorIdAndFmeaId(fmeaStructureDto1.getId(), fmeaId);
                    fmeaStructureDtoList2=setState(fmeaStructureDtoList2, 2);
                    for(FmeaStructureDto fmeaStructureDto2:fmeaStructureDtoList2){
                        List<FmeaStructureDto> fmeaStructureDtoList3 = fmeaStructureMapper.selectBySuperiorIdAndFmeaId(fmeaStructureDto2.getId(), fmeaId);
                        fmeaStructureDtoList3=setState(fmeaStructureDtoList3, 3);
                        fmeaStructureDto2.setFmeaStructureDtoList(fmeaStructureDtoList3);
                    }
                    fmeaStructureDto1.setFmeaStructureDtoList(fmeaStructureDtoList2);
                }
                fmeaStructureDto.setFmeaStructureDtoList(fmeaStructureDtoList1);
            }
            return Result.success(fmeaStructureDtoList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }


    public List<FmeaStructureDto> setState( List<FmeaStructureDto> fmeaStructureDtoList,int state){
        List<FmeaStructureDto> fmeaStructureDtoList1=new ArrayList<>();
        for(FmeaStructureDto fmeaStructureDto:fmeaStructureDtoList){
            fmeaStructureDto.setState(state);
            fmeaStructureDtoList1.add(fmeaStructureDto);
        }
        return fmeaStructureDtoList1;
    }

    /**
     * @Author yyk
     * @Description //TODO 修改结构
     * @Date 2020/5/22 10:29
     * @Param [id, part]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result updateStructureById( int id, String structureName,  String structureDesc) {
        try {
            //查询出原来的数据
            FmeaStructure fmeaStructure = fmeaStructureMapper.selectByPrimaryKey(id);
            //老的结构名称
            String oldContent="结构："+fmeaStructure.getStructureName()+";"+"描述："+fmeaStructure.getStructureDesc();
            //修改fmea结构
            fmeaStructureMapper.updateStructureById(id,structureName,structureDesc);
            //新的结构名称
            String newContent="结构："+structureName+";"+"描述："+structureDesc;
            //封装的结构名称（结构+具体结构名称）
            String s = getStructure(id) + "：" + fmeaStructure.getStructureName();
            FmeaResume fmeaResume = new FmeaResume(s,CommParam.STEP0,newContent,oldContent, DateUtil.parseTime(new Date()),"","",fmeaStructure.getFmeaId());
            //生成修改履历
            fmeaResumeService.insert(fmeaResume);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id删除，结构下有子集同时删除
     * @Date 2020/5/23 8:08
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(int id) {
        try {
            fmeaStructureMapper.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据上级结构查询对应的下级结构
     * @Date 2020/5/30 11:00
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectNextStructureByParentStructure(int structureId) {
        try {
            //查询对应的结构
            FmeaStructure fmeaStructure = fmeaStructureMapper.selectByPrimaryKey(structureId);
            List<FmeaStructureDto> fmeaStructureDtoList = fmeaStructureMapper.selectBySuperiorIdAndFmeaId(structureId, fmeaStructure==null?0:fmeaStructure.getFmeaId());
            return Result.success(fmeaStructureDtoList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id获取其所在结构级别
     * @Date 2020/5/22 16:12
     * @Param [id]
     * @return java.lang.String
     **/
    public String getStructure(int id){
        FmeaStructure fmeaStructure = fmeaStructureMapper.selectByPrimaryKey(id);
        if(fmeaStructure.getSuperiorId()==0)
            return CommParam.STRUCTURE0;
        FmeaStructure fmeaStructure1 = fmeaStructureMapper.selectByPrimaryKey(fmeaStructure.getSuperiorId());
        if(fmeaStructure1.getSuperiorId()==0)
            return CommParam.STRUCTURE1;
        FmeaStructure fmeaStructure2 = fmeaStructureMapper.selectByPrimaryKey(fmeaStructure1.getSuperiorId());
        if(fmeaStructure2.getSuperiorId()==0)
            return CommParam.STRUCTURE2;
        FmeaStructure fmeaStructure3 = fmeaStructureMapper.selectByPrimaryKey(fmeaStructure2.getSuperiorId());
        if(fmeaStructure3.getSuperiorId()==0)
            return CommParam.STRUCTURE3;
        else
        return null;
    }


}
