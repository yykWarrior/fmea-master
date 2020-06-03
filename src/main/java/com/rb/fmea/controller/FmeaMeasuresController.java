package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaMeasures;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaMeasuresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Result delete(String ids){
        return fmeaMeasuresService.delete(ids);
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
    public Result update(FmeaMeasures fmeaMeasures){
        return fmeaMeasuresService.update(fmeaMeasures);
    }



}
