package com.rb.fmea.controller;

import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.ReviewResumeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: ReviewResumeController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/23 9:21
 */
@RestController
public class ReviewResumeController {
    @Autowired
    private ReviewResumeService reviewResumeService;

    /**
     * @Author yyk
     * @Description //TODO 分页查询fmea履历信息
     * @Date 2020/6/23 9:22
     * @Param [pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    @RequestMapping(value = "reviewResume/findByPage",method = RequestMethod.POST)
    @ApiOperation(value = "分页查询fmea履历信息",notes = "分页查询fmea履历信息")
    public ResultDto findByPage(PageParameter pageParameter,String fmeaName){
        return reviewResumeService.findByPage(pageParameter,fmeaName);
    }
}
