package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaMilestoneTime;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaMilestoneTimeService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 16:10
 */
public interface FmeaMilestoneTimeService {
    /**
     * @Author yyk
     * @Description //TODO 添加里程碑数据
     * @Date 2020/6/3 16:11
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(FmeaMilestoneTime fmeaMilestoneTime);

    /**
     * @Author yyk
     * @Description //TODO 修改里程碑数据
     * @Date 2020/6/3 16:14
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaMilestoneTime fmeaMilestoneTime);

    /**
     * @Author yyk
     * @Description //TODO 根据id删除里程碑数据
     * @Date 2020/6/3 16:18
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(int id);

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询
     * @Date 2020/6/3 16:21
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByFmeaId(int fmeaId);
}
