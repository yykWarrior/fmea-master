package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaMilestoneTimeMapper;
import com.rb.fmea.entities.FmeaMilestoneTime;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaMilestoneTimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: FmeaMilestoneTimeServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 16:10
 */
@Service
public class FmeaMilestoneTimeServiceImpl implements FmeaMilestoneTimeService {
    @Resource
    private FmeaMilestoneTimeMapper fmeaMilestoneTimeMapper;
    /**
     * @Author yyk
     * @Description //TODO 添加里程碑数据
     * @Date 2020/6/3 16:11
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(FmeaMilestoneTime fmeaMilestoneTime) {
        try {
            fmeaMilestoneTimeMapper.insert(fmeaMilestoneTime);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改里程碑数据
     * @Date 2020/6/3 16:15
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaMilestoneTime fmeaMilestoneTime) {
        try {
            fmeaMilestoneTimeMapper.updateByPrimaryKey(fmeaMilestoneTime);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id删除里程碑数据
     * @Date 2020/6/3 16:19
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(int id) {
        try {
            fmeaMilestoneTimeMapper.deleteByPrimaryKey(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询
     * @Date 2020/6/3 16:21
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByFmeaId(int fmeaId) {
        try {
            FmeaMilestoneTime fmeaMilestoneTime=fmeaMilestoneTimeMapper.selectByFmeaId(fmeaId);
            return Result.success(fmeaMilestoneTime);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }
}
