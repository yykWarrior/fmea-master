package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaResumeMapper;
import com.rb.fmea.entities.FmeaResume;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.FmeaResumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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


    /**
     * @Author yyk
     * @Description //TODO 分页查询单个fmea下的履历信息
     * @Date 2020/6/17 8:16
     * @Param [fmeaId]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto selectByFmeaId(int fmeaId, PageParameter pageParameter) {
        try {
            int page=(pageParameter.getPage()-1)*pageParameter.getLimit();
            List<FmeaResume> fmeaResumeList=fmeaResumeMapper.selectByFmeaId(fmeaId,page,pageParameter.getLimit());
            //总条数
            int count=fmeaResumeMapper.count(fmeaId);
            return new ResultDto(0,"",count,fmeaResumeList);
        } catch (Exception e) {
            return new ResultDto(1,e.getMessage());
        }
    }
}
