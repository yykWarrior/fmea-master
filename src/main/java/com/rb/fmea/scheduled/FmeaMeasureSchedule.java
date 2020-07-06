package com.rb.fmea.scheduled;

import com.rb.fmea.dao.MeasuresMonitorMapper;
import com.rb.fmea.service.MeasuresMonitorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: FmeaMeasureSchedule
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/20 10:57
 */
@Component
public class FmeaMeasureSchedule {
    @Resource
    private MeasuresMonitorService measuresMonitorService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void setMeasure(){
        measuresMonitorService.insert();
    }
}
