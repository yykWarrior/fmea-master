package com.rb.fmea.controller;

import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.ApMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @version v1.0
 * @ClassName: ApMonitorController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/10 15:35
 */

@RestController
@Api(description = "ap值监控")
public class ApMonitorController {
    @Autowired
    private ApMonitorService apMonitorService;

    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下的ap监控值
     * @Date 2020/6/10 15:45
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "/apMonitor/select_one_fmea",method = RequestMethod.GET)
    @ApiOperation(value = "查询一个fmea下的ap监控值",notes = "查询一个fmea下的ap监控值")
    public Result selectOneFmea(int fmeaId){
        return apMonitorService.selectOneFmea(fmeaId);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下高风险ap值
     * @Date 2020/6/16 11:10
     * @Param [fmeaId]
     * @return com.rb.fmea.result.ResultDto
     **/
    @RequestMapping(value = "/apMonitor/select_hVal",method = RequestMethod.GET)
    @ApiOperation(value = "查询一个fmea下的高风险ap值",notes = "查询一个fmea下的高风险ap值")
    public ResultDto selectHValue(int fmeaId, PageParameter pageParameter){
        return apMonitorService.selectHValue(fmeaId,pageParameter);
    }

}
