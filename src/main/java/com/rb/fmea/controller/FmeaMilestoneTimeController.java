package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaMilestoneTime;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaMilestoneTimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: FmeaMilestoneTime
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 16:00
 */
@Api(description = "fmea基础信息-里程碑时间接口")
@RestController
public class FmeaMilestoneTimeController {
    @Autowired
    private FmeaMilestoneTimeService fmeaMilestoneTimeService;

    /**
     * @Author yyk
     * @Description //TODO 添加里程碑数据
     * @Date 2020/6/3 16:06
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "添加里程碑数据",notes = "添加里程碑数据")
    @RequestMapping(value = "/milestone/insert",method = RequestMethod.POST)
    public Result insert(FmeaMilestoneTime fmeaMilestoneTime){
        return fmeaMilestoneTimeService.insert(fmeaMilestoneTime);
    }

    /**
     * @Author yyk
     * @Description //TODO 修改里程碑数据
     * @Date 2020/6/3 16:13
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改里程碑数据",notes = "修改里程碑数据")
    @RequestMapping(value = "/milestone/update",method = RequestMethod.POST)
    public Result update(FmeaMilestoneTime fmeaMilestoneTime){
        return fmeaMilestoneTimeService.update(fmeaMilestoneTime);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id删除里程碑数据
     * @Date 2020/6/3 16:18
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除里程碑数据",notes = "删除里程碑数据")
    @RequestMapping(value = "/milestone/delete/{id}",method = RequestMethod.GET)
    public Result delete(@PathVariable("id") int id){
        return fmeaMilestoneTimeService.delete(id);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询
     * @Date 2020/6/3 16:20
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "根据fmeaid查询里程碑数据",notes = "根据fmeaid查询里程碑数据")
    @RequestMapping(value = "/milestone/selectByFmeaId/{fmeaId}",method = RequestMethod.GET)
    public Result selectByFmeaId(@PathVariable("fmeaId") int fmeaId){
        return fmeaMilestoneTimeService.selectByFmeaId(fmeaId);
    }
}
