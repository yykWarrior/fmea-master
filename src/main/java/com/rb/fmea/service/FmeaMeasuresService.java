package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaMeasures;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaMeasuresService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/2 9:41
 */
public interface FmeaMeasuresService {
    /**
     * @Author yyk
     * @Description //TODO 批量插入一个失效分析的措施
     * @Date 2020/6/2 9:41
     * @Param [measures]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(String measures, int failAnalysisId, int category, int optimize);

    /**
     * @Author yyk
     * @Description //TODO 删除措施
     * @Date 2020/6/2 10:51
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(String ids);

    /**
     * @Author yyk
     * @Description //TODO 修改措施
     * @Date 2020/6/2 11:02
     * @Param [fmeaMeasures]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaMeasures fmeaMeasures);
}
