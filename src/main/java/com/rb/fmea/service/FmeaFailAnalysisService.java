package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaFailAnalysis;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaFailAnalysisService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/29 11:29
 */
public interface FmeaFailAnalysisService {
    /**
     * @Author yyk
     * @Description //TODO 删除失效分析
     * @Date 2020/5/29 11:31
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(String ids);

    /**
     * @Author yyk
     * @Description //TODO 修改失效分析
     * @Date 2020/5/29 13:13
     * @Param [fmeaFailAnalysis]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaFailAnalysis fmeaFailAnalysis);

    /**
     * @Author yyk
     * @Description //TODO 失效分析之间建立关系
     * @Date 2020/5/29 13:20
     * @Param [parentFailAnalysisId, nextFailAnalysisIds]
     * @return com.rb.fmea.result.Result
     **/
    Result setFmeaFailAnalysisRelate(int parentFailAnalysisId, String nextFailAnalysisIds);

    /**
     * @Author yyk
     * @Description //TODO 在一个功能下添加多个功能分析
     * @Date 2020/5/29 14:27
     * @Param [functionId, fmeaFailAnalysis]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(int functionId, String fmeaFailAnalysis);

    /**
     * @Author yyk
     * @Description //TODO 查询当前功能的上级功能和下级功能对应的失效分析
     * @Date 2020/5/29 17:06
     * @Param [functionId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectFmeaFailAnalysisRelate(int structureId);

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的所有失效分析
     * @Date 2020/6/1 15:38
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectOneStructure(int structureId);

    /**
     * @Author yyk
     * @Description //TODO 查询当前失效分析对应功能的的下级关联功能的所有失效分析
     * @Date 2020/6/1 15:52
     * @Param [fmeaFailAnalysisId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectNextFunction(int fmeaFailAnalysisId);
}
