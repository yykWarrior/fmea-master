package com.rb.fmea.controller;

import com.rb.fmea.result.Result;
import com.rb.fmea.service.MeasuresMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: MeasuresMonitorController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/20 14:25
 */
@RestController
@Api(description = "措施监控状态统计接口")
public class MeasuresMonitorController {
    @Autowired
    private MeasuresMonitorService measuresMonitorService;

    /**
     * @Author yyk
     * @Description //TODO 措施监控每种类型每个月数据
     * @Date 2020/6/20 14:29
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "措施监控每种类型每个月数据",notes = "措施监控每种类型每个月数据")
    @RequestMapping(value = "/measureMonitor/selectByMon/{productId}",method = RequestMethod.GET)
    public Result selectByTypeOrderMon(@PathVariable("productId") int productId){
        return measuresMonitorService.selectByTypeOrderMon(productId);
    }
}
