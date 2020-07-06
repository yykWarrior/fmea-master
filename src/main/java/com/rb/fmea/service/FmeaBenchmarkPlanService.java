package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaBenchmarkPlan;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaBenchmarkPlanService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 17:37
 */
public interface FmeaBenchmarkPlanService {
    /**
     * @Author yyk
     * @Description //TODO 插入fmea的基准计划
     * @Date 2020/6/4 8:42
     * @Param [fmeaBenchmarkPlan]
     * @return com.rb.fmea.result.Result
     *
     * @param fmeaBenchmarkPlan*/
    Result insert(String fmeaBenchmarkPlan);

    /**
     * @Author yyk
     * @Description //TODO 修改基准计划
     * @Date 2020/6/4 15:17
     * @Param [fmeaBenchmarkPlan]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaBenchmarkPlan fmeaBenchmarkPlan);

    /**
     * @Author yyk
     * @Description //TODO 根据id删除基准计划
     * @Date 2020/6/4 15:17
     * @Param [id]
     * @return com.rb.fmea.result.Result
     *
     * @param id*/
    Result delete(String id);

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询
     * @Date 2020/6/4 15:18
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByFmeaId(int fmeaId);
}
