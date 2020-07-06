package com.rb.fmea.controller;

import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.FmeaResumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @version v1.0
 * @ClassName: FmeaResumeController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/17 8:11
 */

@Api(description = "fmea履历接口")
@RestController
public class FmeaResumeController {
    @Autowired
    private FmeaResumeService fmeaResumeService;

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询对应下的履历，根据时间排序
     * @Date 2020/6/17 8:12
     * @Param [fmeaId]
     * @return com.rb.fmea.result.ResultDto
     **/
    @RequestMapping(value = "resume/selectByFmeaId",method = RequestMethod.GET)
    @ApiOperation(value = "查询单个fmea下的履历信息",notes = "查询单个fmea下的履历信息")
    public ResultDto selectByFmeaId(int fmeaId, PageParameter pageParameter){
        return fmeaResumeService.selectByFmeaId(fmeaId,pageParameter);
    }
}
