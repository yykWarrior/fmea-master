package com.rb.fmea.dao;

import com.rb.fmea.entities.ApPriority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @version v1.0
 * @ClassName: ApPriorityMapper
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/5 15:00
 */
@Mapper
public interface ApPriorityMapper {

    /**
     * @Author yyk
     * @Description //TODO 根据频度，探测度和严重度查询ap值
     * @Date 2020/6/5 15:01
     * @Param [frequencyDegree, detectionDegree, severity]
     * @return com.rb.fmea.entities.ApPriority
     **/
    ApPriority selectFrequencyDegreeAnDetectionDegreeAndSeverity(@Param("frequencyDegree") int frequencyDegree, @Param("detectionDegree") int detectionDegree,@Param("severity") int severity);

}
