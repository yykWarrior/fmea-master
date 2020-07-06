package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaMeasures;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.FmeaMeasuresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @version v1.0
 * @ClassName: FmeaMeasuresController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/2 9:21
 */

@RestController
@Api(description = "失效分析措施接口")
public class FmeaMeasuresController {
    @Autowired
    private FmeaMeasuresService fmeaMeasuresService;


    /**
     * @Author yyk
     * @Description //TODO 批量插入一个失效分析的措施
     * @Date 2020/6/2 9:33
     * @Param [measures,category区分预防措施或者探测措施，optimize区分是否优化措施]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "fmeaMeasure/insert",method = RequestMethod.POST)
    @ApiOperation(value = "批量插入措施(包括预防措施,探测措施,优化措施的增加)",notes = "批量插入措施")
    public Result insert(String measures,int failAnalysisId,int category,int optimize){
        return fmeaMeasuresService.insert(measures,failAnalysisId,category,optimize);
    }

    /**
     * @Author yyk
     * @Description //TODO 批量删除措施
     * @Date 2020/6/2 10:54
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "fmeaMeasure/delete/{ids}",method = RequestMethod.GET)
    @ApiOperation(value = "删除措施",notes = "删除措施")
    public Result delete(String ids,int fmeaId){
        return fmeaMeasuresService.delete(ids,fmeaId);
    }


    /**
     * @Author yyk
     * @Description //TODO 修改fmea措施
     * @Date 2020/6/2 11:00
     * @Param [fmeaMeasures]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "fmeaMeasure/update",method = RequestMethod.POST)
    @ApiOperation(value = "修改措施",notes = "修改措施")
    public Result update(FmeaMeasures fmeaMeasures,int fmeaId){
        return fmeaMeasuresService.update(fmeaMeasures,fmeaId);
    }


    /**
     * @Author yyk
     * @Description //TODO 根据结构id查询对应功能下的失效分析下的措施
     * @Date 2020/6/9 13:27
     * @Param [structureId]
     * @return com.rb.fmea.result.ResultDto
     **/
    @RequestMapping(value = "fmeaMeasure/selectByStructureId",method = RequestMethod.GET)
    @ApiOperation(value = "根据结构id查询对应的措施",notes = "根据结构id查询对应的措施")
    public ResultDto selectByStructureId(int structureId,int page,int limit){
        return fmeaMeasuresService.selectByStructureId(structureId,page,limit);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据结构id查询对应功能下的失效分析下的优化措施
     * @Date 2020/6/10 9:49
     * @Param
     * @return
     **/
    @RequestMapping(value = "fmeaMeasure/selectByStructureIdAndOpt",method = RequestMethod.GET)
    @ApiOperation(value = "根据结构id查询对应的优化措施",notes = "根据结构id查询对应的优化措施")
    public ResultDto selectByStructureIdAndOpt(int structureId,int page,int limit){
        return fmeaMeasuresService.selectByStructureIdAndOpt(structureId,page,limit);
    }


    /**
     * @Author yyk
     * @Description //TODO 一个产品下的优化措施监控
     * @Date 2020/6/19 16:43
     * @Param [structureId, page, limit]
     * @return com.rb.fmea.result.ResultDto
     **/
    @RequestMapping(value = "fmeaMeasure/selectOptMeasureOneProduct",method = RequestMethod.POST)
    @ApiOperation(value = "一个产品下的优化措施监控",notes = "一个产品下的优化措施监控")
    public ResultDto selectOptMeasureOneProduct(int productId, PageParameter pageParameter){
        return fmeaMeasuresService.selectOptMeasureOneProduct(productId,pageParameter);
    }


    /**
     * @Author yyk
     * @Description //TODO 修改不执行状态
     * @Date 2020/6/20 9:03
     * @Param [measure]
     * @return com.rb.fmea.result.ResultDto
     **/
    @RequestMapping(value = "fmeaMeasure/updateState/{measureId}",method = RequestMethod.GET)
    @ApiOperation(value = "修改不执行状态",notes = "修改不执行状态")
    public Result updateState(@PathVariable("measureId") int measureId){
        return fmeaMeasuresService.updateState(measureId);
    }


    /**
     * @Author yyk
     * @Description //TODO 点击完成，改变状态,上传文件
     * @Date 2020/6/20 10:32
     * @Param [multipartFile]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "fmeaMeasure/updateStateFinsh",method = RequestMethod.POST)
    @ApiOperation(value = "点击完成,改变状态",notes = "点击完成,改变状态")
    public Result updateStateFinsh(MultipartFile multipartFile,int measureId){
        return fmeaMeasuresService.updateStateFinsh(multipartFile,measureId);
    }




}
