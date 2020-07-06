package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaFunction;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaFunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version v1.0
 * @ClassName: FmeaFunctionController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/23 16:52
 */
@Api(description = "fmea功能接口")
@RestController
public class FmeaFunctionController {
    @Autowired
    private FmeaFunctionService fmeaFunctionService;


    /**
     * @Author yyk
     * @Description //TODO 功能添加
     * @Date 2020/5/23 16:54
     * @Param [functions]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "功能增加接口",notes = "功能增加接口")
    @RequestMapping(value = "fmeaFunction/insert",method = RequestMethod.POST)
    public Result insert(String functions, int structureId){
        return fmeaFunctionService.insert(functions,structureId);
    }


    /**
     * @Author yyk
     * @Description //TODO 删除功能
     * @Date 2020/5/25 9:41
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除功能",notes = "删除功能")
    @RequestMapping(value = "fmeaFunction/delete/{ids}",method = RequestMethod.GET)
    public Result delete(@PathVariable("ids") String ids,int fmeaId){
        return fmeaFunctionService.delete(ids,fmeaId);
    }

    /**
     * @Author yyk
     * @Description //TODO 修改功能
     * @Date 2020/5/25 9:49
     * @Param [fmeaFunction]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改功能",notes = "修改功能")
    @RequestMapping(value = "fmeaFunction/update",method = RequestMethod.POST)
    public Result update(FmeaFunction fmeaFunction,int fmeaId){
        return fmeaFunctionService.update(fmeaFunction,fmeaId);

    }


    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的所有功能
     * @Date 2020/5/25 13:14
     * @Param [StructureId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询单个结构下的功能",notes = "查询单个结构下的功能")
    @RequestMapping(value = "fmeaFunction/selectOneStructure/{structureId}",method = RequestMethod.GET)
    public Result selectOneStructure(@PathVariable("structureId") int structureId){
        return fmeaFunctionService.selectOneStructure(structureId);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下的所有功能
     * @Date 2020/5/25 13:39
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询单个fmea下的功能",notes = "查询单个fmea下的功能")
    @RequestMapping(value = "fmeaFunction/selectOneFmea/{fmeaId}",method = RequestMethod.GET)
    public Result selectOneFmea(@PathVariable("fmeaId") int fmeaId){
        return fmeaFunctionService.selectOneFmea(fmeaId);
    }


    /**
     * @Author yyk
     * @Description //TODO 一个fmea下各个结构的功能之间建立关系
     * @Date 2020/5/25 14:15
     * @Param [structureId0, structureId1]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "fmeaFunction/setFunctionRelate/{functionId0}/{functionId1}",method = RequestMethod.GET)
    @ApiOperation(value = "一个fmea下各个结构的功能之间建立关系",notes = "一个fmea下各个结构的功能之间建立关系")
    public Result setFunctionRelate(@PathVariable("functionId0") int functionId0,@PathVariable("functionId1") String functionIds){
        return fmeaFunctionService.setFunctionRelate(functionId0,functionIds);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的上级结构和自己以及下级结构功能
     * @Date 2020/5/25 15:42
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询一个结构下的上级结构和自己以及下级结构功能",notes = "查询一个结构下的上级结构和自己以及下级结构功能")
    @RequestMapping(value = "fmeaFunction/selectFunctionRelate/{structureId}",method = RequestMethod.GET)
    public Result selectFunctionRelate(@PathVariable("structureId")int structureId){
        return fmeaFunctionService.selectFunctionRelate(structureId);
    }

    /**
     * @Author yyk
     * @Description //TODO 查询当前结构的下级结构的所有功能
     * @Date 2020/5/30 10:52
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询当前结构的下级结构的所有功能",notes = "查询当前结构的下级结构的所有功能")
    @RequestMapping(value = "fmeaFunction/selectNextStructureAndFunction/{structureId}",method = RequestMethod.GET)
    public Result selectNextStructure(@PathVariable("structureId") int structureId){
        return fmeaFunctionService.selectNextStructureAndFunction(structureId);
    }
}
