package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaBasicInformation;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaBasicInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: FmeaBasicInformationController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 15:07
 */
@RestController
@Api(description = "fmea基础信息接口")
public class FmeaBasicInformationController {
    @Autowired
    private FmeaBasicInformationService fmeaBasicInformationService;

    /**
     * @Author yyk
     * @Description //TODO 插入fmea的基础信息
     * @Date 2020/6/3 15:09
     * @Param [fmeaBasicInformation]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "添加fmea基础信息",notes = "添加fmea基础信息")
    @RequestMapping(value = "/basic/insert",method = RequestMethod.POST)
    public Result insert(FmeaBasicInformation fmeaBasicInformation){
        return fmeaBasicInformationService.insert(fmeaBasicInformation);
    }

    /**
     * @Author yyk
     * @Description //TODO 修改fmea的基础信息
     * @Date 2020/6/3 15:19
     * @Param [fmeaBasicInformation]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改fmea的基础信息",notes = "修改fmea的基础信息")
    @RequestMapping(value = "/basic/update",method = RequestMethod.POST)
    public Result update(FmeaBasicInformation fmeaBasicInformation){
        return fmeaBasicInformationService.update(fmeaBasicInformation);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id删除fmea信息
     * @Date 2020/6/3 15:22
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除fmea的基础信息",notes = "删除fmea的基础信息")
    @RequestMapping(value = "/basic/delete/{id}",method = RequestMethod.GET)
    public Result  delete(@PathVariable("id") int id){
        return fmeaBasicInformationService.delete(id);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmea的id查询fmea的基础信息
     * @Date 2020/6/3 15:26
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "根据fmea的id查询fmea的基础信息",notes = "根据fmea的id查询fmea的基础信息")
    @RequestMapping(value = "/basic/selectByFmeaId/{fmeaId}",method = RequestMethod.GET)
    public Result selectByFmeaId(@PathVariable("fmeaId") int fmeaId){
        return fmeaBasicInformationService.selectByFmeaId(fmeaId);
    }
}
