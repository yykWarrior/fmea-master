package com.rb.fmea.service;

import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaStructureService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 15:48
 */
public interface FmeaStructureService {

    /**
     * @Author yyk
     * @Description //TODO 插入客户和总成
     * @Date 2020/5/21 15:49
     * @Param [fmeaId, customer, assembly]
     * @return com.rb.fmea.result.Result
     **/
    Result insertCustomerAndAssembly(int fmeaId, String customer, String assembly,String customerDesc,String assemblyDesc);

    /**
     * @Author yyk
     * @Description //TODO 在总成下插入零件
     * @Date 2020/5/21 16:13
     * @Param [assemblyId, parts]
     * @return com.rb.fmea.result.Result
     **/
    Result insertParts(int assemblyId, String parts);

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaId查询
     * @Date 2020/5/21 16:57
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByFmeaId(int fmeaId);

    /**
     * @Author yyk
     * @Description //TODO 修改零件
     * @Date 2020/5/22 10:29
     * @Param [id, part]
     * @return com.rb.fmea.result.Result
     **/
    Result updateStructureById(int id, String structureName, String structureDesc);

    /**
     * @Author yyk
     * @Description //TODO 根据id删除结构，结构下有子集同时删除
     * @Date 2020/5/23 8:07
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(int id);

    /**
     * @Author yyk
     * @Description //TODO 根据上级结构查询对应的下级结构
     * @Date 2020/5/30 10:59
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectNextStructureByParentStructure(int structureId);
}
