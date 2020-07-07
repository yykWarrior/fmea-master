package com.rb.fmea.scheduled;

import com.rb.fmea.service.ReviewResultSumService;
import com.rb.fmea.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @ClassName: ReviewSchedule
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/24 16:46
 */
@Component
public class ReviewSchedule {
    @Autowired
    private ReviewResultSumService reviewResultSumService;
    @Autowired
    private ReviewService reviewService;
    //每月1号
    @Scheduled(cron = "0 0 0 1 * ?")
    public void setReview(){
        reviewResultSumService.insert();
    }

    //每天1点
    @Scheduled(cron = "0 0 1 * * ?")
    public void setNotCreateTimeOut(){
        reviewService.updateState();
    }
}
