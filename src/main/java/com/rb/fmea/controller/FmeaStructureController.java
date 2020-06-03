package com.rb.fmea.controller;


import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaStructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: FmeaStructureController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 15:39
 */
@Api(description = "fmea结构接口")
@RestController
public class FmeaStructureController {
    @Autowired
    private FmeaStructureService fmeaStructureService;

    /**
     * @Author yyk
     * @Description //TODO 插入客户和总成
     * @Date 2020/5/21 15:44
     * @Param [fmeaId, customer, assembly]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "插入客户和总成",notes = "插入客户和总成")
    @RequestMapping(value = "fmeaStructure/insertCustomerAndAssembly",method = RequestMethod.POST)
    public Result insertCustomerAndAssembly(int fmeaId,String customer,String assembly,String customerDesc,String assemblyDesc){
       return fmeaStructureService.insertCustomerAndAssembly(fmeaId,customer,assembly, customerDesc, assemblyDesc);
    }


    /**
     * @Author yyk
     * @Description //TODO 在总成下添加零件
     * @Date 2020/5/21 16:11
     * @Param [assemblyId, parts]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "插入零件",notes = "插入零件")
    @RequestMapping(value = "fmeaStructure/insertParts",method = RequestMethod.POST)
    public Result insertParts(int assemblyId,String parts){
        return fmeaStructureService.insertParts(assemblyId,parts);
    }


    /**
     * @Author yyk
     * @Description //TODO 插入特性
     * @Date 2020/5/21 16:24
     * @Param [partId, features]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "插入特性",notes = "插入特性")
    @RequestMapping(value = "fmeaStructure/insertFeatures",method = RequestMethod.POST)
    public Result insertFeatures(int partId,String features){
        return fmeaStructureService.insertParts(partId,features);
    }


    /**
     * @Author yyk
     * @Description //TODO 一个fmea下的分级查询
     * @Date 2020/5/21 16:55
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "一个fmea下的分级查询",notes = "一个fmea下的分级查询")
    @RequestMapping(value = "fmeaStructure/selectByFmeaId",method = RequestMethod.GET)
    public Result selectByFmeaId(int fmeaId){
        return fmeaStructureService.selectByFmeaId(fmeaId);
    }


    /**
     * @Author yyk
     * @Description //TODO 修改零件
     * @Date 2020/5/22 10:28
     * @Param [id, part]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改结构信息",notes = "修改结构信息")
    @RequestMapping(value = "fmeaStructure/updateStructureById",method = RequestMethod.POST)
    public Result updateStructureById(int id,String structureName,String structureDesc){
        return fmeaStructureService.updateStructureById(id,structureName,structureDesc);
    }


    /**
     * @Author yyk
     * @Description //TODO 根据id删除结构，结构下有子集同时删除
     * @Date 2020/5/23 8:07
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除结构",notes = "删除结构")
    @RequestMapping(value = "fmeaStructure/delete/{id}",method = RequestMethod.POST)
    public Result delete(@PathVariable("id") int id){
        return fmeaStructureService.delete(id);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据上级结构查询对应的下级结构
     * @Date 2020/5/30 10:56
     * @Param []
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "根据上级结构查询对应的下级结构",notes = "根据上级结构查询对应的下级结构")
    @RequestMapping(value = "fmeaStructure/selectNextStructure/{structureId}",method = RequestMethod.GET)
    public Result selectNextStructureByParentStructure(@PathVariable("structureId") int structureId){
        return fmeaStructureService.selectNextStructureByParentStructure(structureId);
    }

}
