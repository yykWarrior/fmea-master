package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaResume;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.ResultDto;

/**
 * @version v1.0
 * @ClassName: FmeaResume
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/22 13:28
 */
public interface FmeaResumeService {


    /**
     * @Author yyk
     * @Description //TODO 添加fmea修改履历
     * @Date 2020/5/22 13:48
     * @Param [fmeaResume]
     * @return void
     **/
    void insert(FmeaResume fmeaResume);

    /**
     * @Author yyk
     * @Description //TODO 分页查询单个fmea下的履历信息
     * @Date 2020/6/17 8:15
     * @Param [fmeaId]
     * @return com.rb.fmea.result.ResultDto
     **/
    ResultDto selectByFmeaId(int fmeaId, PageParameter pageParameter);
}
