package com.rb.fmea.service.impl;

import com.rb.fmea.dao.ReviewResumeMapper;
import com.rb.fmea.entities.ReviewResume;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.ReviewResumeService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: ReviewResumeServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/23 9:24
 */
@Service
public class ReviewResumeServiceImpl implements ReviewResumeService {
    @Resource
    private ReviewResumeMapper reviewResumeMapper;
    /**
     * @Author yyk
     * @Description //TODO 分页查询fmea履历信息
     * @Date 2020/6/23 9:32
     * @Param [pageParameter, fmeaName]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto findByPage(PageParameter pageParameter, String fmeaName) {
        try {
            int page=(pageParameter.getPage()-1)*pageParameter.getLimit();
            fmeaName = StringProcess.stringConcate(fmeaName, "%");
            List<ReviewResume> reviewResumeList= reviewResumeMapper.selectLikeFmeaName(fmeaName,page,pageParameter.getLimit());
            //查询总条数
            int count=reviewResumeMapper.count(fmeaName);
            return new ResultDto(0,"",count,reviewResumeList);
        } catch (Exception e) {
            return new ResultDto(1,e.getMessage());
        }
    }
}
