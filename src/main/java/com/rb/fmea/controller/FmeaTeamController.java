package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaTeam;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaTeamService;
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
 * @ClassName: FmeaTeamController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 16:31
 */

@Api(description = "fmea基础信息-小组信息接口")
@RestController
public class FmeaTeamController {
    @Autowired
    private FmeaTeamService fmeaTeamService;


    /**
     * @Author yyk
     * @Description //TODO 添加小组信息
     * @Date 2020/6/3 16:06
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "添加小组信息",notes = "添加小组信息")
    @RequestMapping(value = "/team/insert",method = RequestMethod.POST)
    public Result insert(String fmeaTeams){
        return fmeaTeamService.insert(fmeaTeams);
    }

    /**
     * @Author yyk
     * @Description //TODO 修改小组信息
     * @Date 2020/6/3 16:13
     * @Param [fmeaMilestoneTime]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改小组信息",notes = "修改小组信息")
    @RequestMapping(value = "/team/update",method = RequestMethod.POST)
    public Result update(FmeaTeam fmeaTeam){
        return fmeaTeamService.update(fmeaTeam);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id小组信息
     * @Date 2020/6/3 16:18
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除小组信息",notes = "删除小组信息")
    @RequestMapping(value = "/team/delete/{ids}",method = RequestMethod.GET)
    public Result delete(@PathVariable("ids") String ids){
        return fmeaTeamService.delete(ids);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询小组信息
     * @Date 2020/6/3 16:20
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "根据fmeaid查询小组信息",notes = "根据fmeaid查询小组信息")
    @RequestMapping(value = "/team/selectByFmeaId/{fmeaId}",method = RequestMethod.GET)
    public Result selectByFmeaId(@PathVariable("fmeaId") int fmeaId){
        return fmeaTeamService.selectByFmeaId(fmeaId);
    }
}
