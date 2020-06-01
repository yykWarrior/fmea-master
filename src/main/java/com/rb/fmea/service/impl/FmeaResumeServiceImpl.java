package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaResumeMapper;
import com.rb.fmea.entities.FmeaResume;
import com.rb.fmea.service.FmeaResumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: FmeaResumeServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/22 13:29
 */
@Service
public class FmeaResumeServiceImpl implements FmeaResumeService {
    @Resource
    private FmeaResumeMapper fmeaResumeMapper;

    /**
     * @Author yyk
     * @Description //TODO 添加fmea修改履历
     * @Date 2020/5/22 13:48
     * @Param [fmeaResume]
     * @return void
     **/
    @Override
    public void insert(FmeaResume fmeaResume) {
        int insert = fmeaResumeMapper.insert(fmeaResume);

    }
}
