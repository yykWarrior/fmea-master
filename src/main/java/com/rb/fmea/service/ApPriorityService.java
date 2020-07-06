package com.rb.fmea.service;

import com.rb.fmea.entities.ApPriority;

/**
 * @version v1.0
 * @ClassName: ApPrioorityService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/5 14:54
 */
public interface ApPriorityService {
    ApPriority selectFrequencyDegreeAnDetectionDegreeAndSeverity(int frequencyDegree,int detectionDegree,int severity);
}
