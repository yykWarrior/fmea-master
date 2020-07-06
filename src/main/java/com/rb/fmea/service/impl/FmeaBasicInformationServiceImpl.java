package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaBasicInformationMapper;
import com.rb.fmea.entities.FmeaBasicInformation;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaBasicInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: FmeaBasicInformationServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 15:12
 */
@Service
public class FmeaBasicInformationServiceImpl implements FmeaBasicInformationService {
    @Resource
    private FmeaBasicInformationMapper fmeaBasicInformationMapper;
    /**
     * @Author yyk
     * @Description //TODO 插入fmea的基础信息
     * @Date 2020/6/3 15:13
     * @Param [fmeaBasicInformation]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(FmeaBasicInformation fmeaBasicInformation) {
        try {
            //查询是否已经添加
            FmeaBasicInformation fmeaBasicInformation1 = fmeaBasicInformationMapper.selectByFmeaId(fmeaBasicInformation.getFmeaId());
            if(fmeaBasicInformation1==null){
                fmeaBasicInformationMapper.insert(fmeaBasicInformation);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改fmea的基础信息
     * @Date 2020/6/3 15:20
     * @Param [fmeaBasicInformation]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaBasicInformation fmeaBasicInformation) {
        try {
            fmeaBasicInformationMapper.updateByPrimaryKey(fmeaBasicInformation);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id删除fmea信息
     * @Date 2020/6/3 15:25
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(int id) {
        try {
            fmeaBasicInformationMapper.deleteByPrimaryKey(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询对应的基础信息
     * @Date 2020/6/3 15:29
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByFmeaId(int fmeaId) {
        try {
            FmeaBasicInformation fmeaBasicInformation=fmeaBasicInformationMapper.selectByFmeaId(fmeaId);
            return Result.success(fmeaBasicInformation);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }


}
