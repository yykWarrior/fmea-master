package com.rb.fmea.controller;

import com.rb.fmea.entities.Review;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: ReviewController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/22 10:46
 */
@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /**
     * @Author yyk
     * @Description //TODO 新建评审,step值为结构id,state值为0表示是总成，1为零件,reviewState,0待建，1完成，2计划，3计划未建超期,4未评审超期
     * @Date 2020/6/22 10:48
     * @Param [review]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "review/insert",method = RequestMethod.POST)
    @ApiOperation(value = "新建评审,step值为结构id,state值为0表示是总成，reviewState,1为零件,0待建，1完成，2计划，3计划未建超期,4未评审超期",notes = "新建评审,step值为结构id,state值为0表示是总成，1为零件")
    public Result insert(Review review){
        return reviewService.insert(review);
    }

    /**
     * @Author yyk
     * @Description //TODO 删除评审
     * @Date 2020/6/22 11:02
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "review/delete/{ids}",method = RequestMethod.GET)
    @ApiOperation(value = "删除评审",notes = "删除评审")
    public Result delete(@PathVariable("ids") String ids){
        return reviewService.delete(ids);
    }

    /**
     * @Author yyk
     * @Description //TODO 修改评审
     * @Date 2020/6/22 11:08
     * @Param [review]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "review/update",method = RequestMethod.POST)
    @ApiOperation(value = "修改评审",notes = "修改评审")
    public Result update(Review review){
        return reviewService.update(review);
    }


    /**
     * @Author yyk
     * @Description //TODO 根据id修改评审结果,实施评审
     * @Date 2020/6/23 9:12
     * @Param [id, reviewResult]
     * @return com.rb.fmea.result.Result
     **/
    @RequestMapping(value = "review/updateById",method = RequestMethod.POST)
    @ApiOperation(value = "实施评审",notes = "实施评审")
    public Result updateById(int id,String reviewResult){
        return reviewService.updateById(id,reviewResult);
    }


    /**
     * @Author yyk
     * @Description //TODO 分页查询评审结果
     * @Date 2020/6/23 16:58
     * @Param [pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    @RequestMapping(value = "reviewResult/selectByPage")
    @ApiOperation(value = "分页查询评审结果",notes = "分页查询评审结果")
    public ResultDto selectByPage(PageParameter pageParameter, int productId){
        return reviewService.selectByPage(pageParameter,productId);
    }

}
