package com.rb.fmea.service.impl;

import com.rb.fmea.dao.ApPriorityMapper;
import com.rb.fmea.entities.ApPriority;
import com.rb.fmea.service.ApPriorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: ApPrioorityServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/5 14:55
 */
@Service
public class ApPriorityServiceImpl implements ApPriorityService {
    @Resource
    private ApPriorityMapper apPriorityMapper;
    /**
     * @Author yyk
     * @Description //TODO 根据频度，探测度和严重度查询ap值
     * @Date 2020/6/5 14:59
     * @Param [frequencyDegree, detectionDegree, severity]
     * @return com.rb.fmea.entities.ApPriority
     **/
    @Override
    public ApPriority selectFrequencyDegreeAnDetectionDegreeAndSeverity(int frequencyDegree, int detectionDegree, int severity) {
        ApPriority apPriority=apPriorityMapper.selectFrequencyDegreeAnDetectionDegreeAndSeverity(frequencyDegree,detectionDegree,severity);
        return apPriority;
    }
}
