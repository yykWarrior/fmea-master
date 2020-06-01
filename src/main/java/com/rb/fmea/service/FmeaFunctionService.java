package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaFunction;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaFunctionService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/23 16:56
 */
public interface FmeaFunctionService {
    /**
     * @Author yyk
     * @Description //TODO 批量增加功能
     * @Date 2020/5/23 16:59
     * @Param [functions]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(String functions, int structureId);

    /**
     * @Author yyk
     * @Description //TODO 批量删除fmea功能
     * @Date 2020/5/25 9:43
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(String ids);

    /**
     * @Author yyk
     * @Description //TODO 修改fmea功能
     * @Date 2020/5/25 9:50
     * @Param [fmeaFunction]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaFunction fmeaFunction);

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的所有功能
     * @Date 2020/5/25 13:17
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectOneStructure(int structureId);

    /**
     * @Author yyk
     * @Description //TODO 查询单个fmea下所有功能
     * @Date 2020/5/25 13:44
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectOneFmea(int fmeaId);

    /**
     * @Author yyk
     * @Description //TODO 一个fmea下各个结构的功能之间建立关系
     * @Date 2020/5/25 14:17
     * @Param [structureId0, structureId1]
     * @return com.rb.fmea.result.Result
     **/
    Result setFunctionRelate(int functionId0, String functionIds);

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的上级结构和自己以及下级结构功能
     * @Date 2020/5/25 15:44
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectFunctionRelate(int structureId);

    /**
     * @Author yyk
     * @Description //TODO 查询当前结构的下级结构的所有功能
     * @Date 2020/5/30 11:16
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectNextStructureAndFunction(int structureId);
}
