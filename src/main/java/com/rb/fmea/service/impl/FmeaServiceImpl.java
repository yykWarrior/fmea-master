package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaMapper;
import com.rb.fmea.dao.FmeaStructureMapper;
import com.rb.fmea.dao.FmeaTypeMapper;
import com.rb.fmea.dao.ReviewMapper;
import com.rb.fmea.dto.FmeaDto;
import com.rb.fmea.dto.FmeaStructureDto;
import com.rb.fmea.entities.Fmea;
import com.rb.fmea.entities.FmeaStructure;
import com.rb.fmea.entities.FmeaType;
import com.rb.fmea.entities.Review;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaService;
import com.rb.fmea.service.ReviewService;
import com.rb.fmea.util.DateUtil;
import com.rb.fmea.util.StringProcess;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version v1.0
 * @ClassName: FmeaServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 10:21
 */
@Service
public class FmeaServiceImpl implements FmeaService {
    @Resource
    private FmeaMapper fmeaMapper;
    @Resource
    private FmeaTypeMapper fmeaTypeMapper;
    @Resource
    private FmeaStructureMapper fmeaStructureMapper;
    @Resource
    private ReviewMapper reviewMapper;
    @Autowired
    private ReviewService reviewService;
    /**
     * @Author yyk
     * @Description //TODO 添加fmea
     * @Date 2020/5/21 10:21
     * @Param [fmea]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(Fmea fmea) {
        try {
            if(fmea.getSuperiorId()==null)
                fmea.setSuperiorId(0);
            if(fmea.getTypeId()!=0){
                FmeaType fmeaType = fmeaTypeMapper.selectByPrimaryKey(fmea.getTypeId());
                String desc=fmeaType==null?"":fmeaType.getTypeName();
                fmea.setFmeaDesc(desc);
            }
            fmeaMapper.insert(fmea);
            return Result.success(fmea.getId());
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 查询所有基础fmea
     * @Date 2020/5/21 10:27
     * @Param []
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectBasicFmea() {
        try {
            List<Fmea> fmeaList=fmeaMapper.selectBasicFmea();
            return Result.success(fmeaList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 修改fmea信息
     * @Date 2020/5/21 10:56
     * @Param [fmea]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(Fmea fmea) {
        try {
            fmeaMapper.updateByPrimaryKey(fmea);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 删除fmea
     * @Date 2020/5/21 11:01
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            String[] idArray = StringProcess.spliteString(ids, ",");
            for(int i=0;i<idArray.length;i++){
                fmeaMapper.delete(Integer.parseInt(idArray[i]));
                //fmeaMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 查询一个产品下对应的基础,家族,新品fmea
     * @Date 2020/5/21 11:20
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByProductId(int productId) {
        try {
            List<FmeaDto> fmeaList=fmeaMapper.selectByProductId(productId,0);
            for(FmeaDto fmea:fmeaList){
                List<FmeaDto> fmeaList1=fmeaMapper.selectByProductId(productId,fmea.getId());
                for(FmeaDto fmea1:fmeaList1){
                    List<FmeaDto> fmeaList2=fmeaMapper.selectByProductId(productId,fmea1.getId());
                    fmea1.setFamilyFmeaList(fmeaList2);
                }
                fmea.setFamilyFmeaList(fmeaList1);
            }
            return Result.success(fmeaList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }


    /**
     * @Author yyk
     * @Description //TODO 查询所有fmea
     * @Date 2020/6/16 9:52
     * @Param []
     * @return java.util.List<com.rb.fmea.entities.Fmea>
     **/
    @Override
    public List<Fmea> selectAll() {
        return fmeaMapper.selectAll();
    }

    /**
     * @Author yyk
     * @Description //TODO 点击fmea完成，更新状态和完成时间,
     * @Date 2020/7/1 16:22
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public synchronized Result updateState(int fmeaId) {
        try {
            //根据fmeaId查询fmea信息
            Fmea fmea = fmeaMapper.selectByPrimaryKey(fmeaId);
            fmea.setFinishDate(DateUtil.parseTime(new Date()));
            fmea.setState(1);
           fmeaMapper.updateByPrimaryKey(fmea);
            //生成评审信息
            //为结构生成评审信息(只为总成生成)
            FmeaStructure fmeaStructure=fmeaStructureMapper.selectSecondTree(fmeaId);
            //为零件生成评审信息
            List<FmeaStructureDto> fmeaStructureDtoList = fmeaStructureMapper.selectBySuperiorIdAndFmeaId(fmeaStructure.getId(), fmeaId);
            //插入总成的评审信息
            reviewService.create(fmeaId,fmeaStructure.getId(),0);
            for(FmeaStructureDto fmeaStructureDto:fmeaStructureDtoList){
                reviewService.create(fmeaId,fmeaStructureDto.getId(),1);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 根据id查询判断fmea状态
     * @Date 2020/7/2 14:20
     * @Param [fmeaId]
     * @return java.lang.Boolean
     **/
    public Boolean selectByIdReturnState(int fmeaId){
        Boolean result=false;
        Fmea fmea = fmeaMapper.selectByPrimaryKey(fmeaId);
        if(fmea!=null){
            int state = fmea.getState();
            result=state==0?false:true;
        }
        return result;
    }

}
