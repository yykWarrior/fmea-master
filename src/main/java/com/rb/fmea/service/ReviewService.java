package com.rb.fmea.service;

import com.rb.fmea.entities.Review;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;

/**
 * @version v1.0
 * @ClassName: ReviewService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/22 10:47
 */
public interface ReviewService {
    Result insert(Review review);

    Result delete(String ids);

    Result update(Review review);

    Result updateById(int id, String reviewResult);

    ResultDto selectByPage(PageParameter pageParameter, int productId);

    void create(int fmeaId, int structId, int state);

    void updateState();
}
