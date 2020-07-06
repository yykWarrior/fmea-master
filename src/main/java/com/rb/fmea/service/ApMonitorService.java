package com.rb.fmea.service;

import com.rb.fmea.entities.ApMonitor;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;

/**
 * @version v1.0
 * @ClassName: ApMonitorService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/10 15:49
 */
public interface ApMonitorService {
    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下的ap监控值
     * @Date 2020/6/10 15:50
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectOneFmea(int fmeaId);

    /**
     * @Author yyk
     * @Description //TODO 计算当月ap监控值
     * @Date 2020/6/16 9:38
     * @Param [fmeaId]
     * @return com.rb.fmea.entities.ApMonitor
     **/
     ApMonitor getApMonitor(int fmeaId);

     /**
      * @Author yyk
      * @Description //TODO 插入监控信息
      * @Date 2020/6/16 9:55
      * @Param [apMonitor]
      * @return void
      **/
    void insert(ApMonitor apMonitor);

    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下高风险ap值
     * @Date 2020/6/16 11:12
     * @Param [fmeaId, pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    ResultDto selectHValue(int fmeaId, PageParameter pageParameter);
}
