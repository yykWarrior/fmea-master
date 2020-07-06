package com.rb.fmea.service;

import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.ResultDto;

/**
 * @version v1.0
 * @ClassName: ReviewResumeService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/23 9:24
 */
public interface ReviewResumeService {
    ResultDto findByPage(PageParameter pageParameter, String fmeaName);
}
