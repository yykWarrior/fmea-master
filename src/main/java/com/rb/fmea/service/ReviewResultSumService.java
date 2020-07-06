package com.rb.fmea.service;

import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: ReviewResultSumService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/24 16:48
 */
public interface ReviewResultSumService {
    void insert();

    Result selectByProductId(int productId);
}
