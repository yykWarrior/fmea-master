package com.rb.fmea.service;

import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: MeasuresMonitorService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/20 13:11
 */
public interface MeasuresMonitorService {
    void insert();

    /**
     * @Author yyk
     * @Description //TODO 措施监控每种类型每个月数据
     * @Date 2020/6/20 14:29
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByTypeOrderMon(int productId);
}
