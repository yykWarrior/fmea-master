package com.rb.fmea.controller;

import com.rb.fmea.result.Result;
import com.rb.fmea.service.ReviewResultSumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: ReviewResultSumController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/24 17:17
 */
@RestController
@Api(description = "评审接口")
public class ReviewResultSumController {
    @Autowired
    private ReviewResultSumService reviewResultSumService;

    /**
     * @Author yyk
     * @Description //TODO 根据产品id查询
     * @Date 2020/6/24 17:19
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "reviewResumeSum/selectByProductId",method = RequestMethod.POST)
    @ApiOperation(value = "根据产品id查询",notes = "根据产品id查询")
    public Result selectByProductId(int productId){
        return reviewResultSumService.selectByProductId(productId);
    }
}
