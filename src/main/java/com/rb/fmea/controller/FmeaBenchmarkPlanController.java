package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaBasicInformation;
import com.rb.fmea.entities.FmeaBenchmarkPlan;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaBenchmarkPlanService;
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
 * @ClassName: FmeaBenchmarkPlanController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 17:34
 */
@Api(description = "fmea基础数据-基准计划接口")
@RestController
public class FmeaBenchmarkPlanController {
    @Autowired
    private FmeaBenchmarkPlanService fmeaBenchmarkPlanService;

    /**
     * @Author yyk
     * @Description //TODO 插入fmea的基准计划
     * @Date 2020/6/3 15:09
     * @Param [fmeaBasicInformation]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "插入fmea的基准计划",notes = "插入fmea的基准计划")
    @RequestMapping(value = "/plan/insert",method = RequestMethod.POST)
    public Result insert(String fmeaBenchmarkPlan){
        return fmeaBenchmarkPlanService.insert(fmeaBenchmarkPlan);
    }



    /**
     * @Author yyk
     * @Description //TODO 修改基准计划
     * @Date 2020/6/3 16:13
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改基准计划",notes = "修改基准计划")
    @RequestMapping(value = "/plan/update",method = RequestMethod.POST)
    public Result update(FmeaBenchmarkPlan fmeaBenchmarkPlan){
        return fmeaBenchmarkPlanService.update(fmeaBenchmarkPlan);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id删除基准计划
     * @Date 2020/6/3 16:18
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除基准计划",notes = "删除基准计划")
    @RequestMapping(value = "/plan/delete/{ids}",method = RequestMethod.GET)
    public Result delete(@PathVariable("ids") String ids){
        return fmeaBenchmarkPlanService.delete(ids);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询
     * @Date 2020/6/3 16:20
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "根据fmeaid查询基准计划",notes = "根据fmeaid查询基准计划")
    @RequestMapping(value = "/plan/selectByFmeaId/{fmeaId}",method = RequestMethod.GET)
    public Result selectByFmeaId(@PathVariable("fmeaId") int fmeaId){
        return fmeaBenchmarkPlanService.selectByFmeaId(fmeaId);
    }

}
