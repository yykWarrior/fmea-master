package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaFailAnalysis;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaFailAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @version v1.0
 * @ClassName: FmeaFailAnalysisController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/29 11:21
 */

@RestController
@Api(description = "失效分析接口")
public class FmeaFailAnalysisController {
    @Autowired
    private FmeaFailAnalysisService fmeaFailAnalysisService;

    /**
     * @Author yyk
     * @Description //TODO 在一个功能下添加多个失效分析
     * @Date 2020/5/29 14:25
     * @Param [functionId, fmeaFailAnalysis]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "在一个功能下添加多个功能分析",notes = "在一个功能下添加多个功能分析")
    @RequestMapping(value = "analysis/insert",method = RequestMethod.POST)
    public Result insert(String fmeaFailAnalysis){
        return fmeaFailAnalysisService.insert(fmeaFailAnalysis);
    }
    /*public Result insert(int functionId,String fmeaFailAnalysisDesc){
        return fmeaFailAnalysisService.insert(functionId,fmeaFailAnalysisDesc);
    }*/


    /**
     * @Author yyk
     * @Description //TODO 批量删除失效分析
     * @Date 2020/5/29 11:31
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除失效分析",notes = "删除失效分析")
    @RequestMapping(value = "analysis/delete/{ids}",method = RequestMethod.GET)
    public Result delete(@PathVariable("ids") String ids,int fmeaId){
        return fmeaFailAnalysisService.delete(ids,fmeaId);
    }

    /**
     * @Author yyk
     * @Description //TODO 修改失效分析
     * @Date 2020/5/29 13:11
     * @Param []
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改失效分析",notes = "修改失效分析")
    @RequestMapping(value = "analysis/update",method = RequestMethod.POST)
    public Result update(FmeaFailAnalysis fmeaFailAnalysis,int fmeaId){
        return fmeaFailAnalysisService.update(fmeaFailAnalysis,fmeaId);
    }


    /**
     * @Author yyk
     * @Description //TODO 失效分析之间建立关系
     * @Date 2020/5/29 13:17
     * @Param [ParentFailAnalysisId, nextFailAnalysisIds]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "失效分析建立联系",notes = "修改失效建立联系")
    @RequestMapping(value = "analysis/setFmeaFailAnalysisRelate/{parentFailAnalysisId}/{nextFailAnalysisIds}",method = RequestMethod.GET)
    public Result setFmeaFailAnalysisRelate(@PathVariable("parentFailAnalysisId") int parentFailAnalysisId,@PathVariable("nextFailAnalysisIds") String nextFailAnalysisIds){
        return fmeaFailAnalysisService.setFmeaFailAnalysisRelate(parentFailAnalysisId,nextFailAnalysisIds);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询当前功能的上级功能和下级功能对应的失效分析
     * @Date 2020/5/29 17:02
     * @Param [functionId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询当前功能的上级功能和下级功能对应的失效分析",notes = "查询当前功能的上级功能和下级功能对应的失效分析")
    @RequestMapping(value = "fmeaFailAnalysisRelate/selectFmeaFailAnalysisRelate/{structureId}",method = RequestMethod.GET)
    public Result selectFmeaFailAnalysisRelate(@PathVariable("structureId") int structureId){
        return fmeaFailAnalysisService.selectFmeaFailAnalysisRelate(structureId);
    }

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的所有失效分析
     * @Date 2020/6/1 15:37
     * @Param [structureId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询一个结构下的失效分析",notes = "查询一个结构下的失效分析")
    @RequestMapping(value = "fmeaFailAnalysisRelate/selectOneStructure/{structureId}",method = RequestMethod.GET)
    public Result selectOneStructure(int structureId){
        return fmeaFailAnalysisService.selectOneStructure(structureId);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询当前失效分析对应功能的的下级关联功能的所有失效分析
     * @Date 2020/6/1 15:50
     * @Param [fmeaFailAnalysisId]
     * @return com.rb.fmea.result.Result
     **/

    @ApiOperation(value = "对应下级功能的失效分析",notes = "对应下级功能的失效分析")
    @RequestMapping(value = "fmeaFailAnalysisRelate/selectNextFunction/{fmeaFailAnalysisId}",method = RequestMethod.GET)
    public Result selectNextFunction(int fmeaFailAnalysisId){
        return fmeaFailAnalysisService.selectNextFunction(fmeaFailAnalysisId);
    }
}
