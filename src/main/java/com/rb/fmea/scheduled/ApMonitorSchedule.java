package com.rb.fmea.scheduled;

import com.rb.fmea.entities.ApMonitor;
import com.rb.fmea.entities.Fmea;
import com.rb.fmea.service.ApMonitorService;
import com.rb.fmea.service.FmeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: ApMonitorSchedule
 * @Description: TODO ap值监控定时任务
 * @Author: yyk
 * @Date: 2020/6/16 9:34
 */
@Component
public class ApMonitorSchedule {
    @Autowired
    private ApMonitorService apMonitorService;
    @Autowired
    private FmeaService fmeaService;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void setApMonitor(){
        //查询出所有的fmea信息
        List<Fmea> fmeaList= fmeaService.selectAll();
        for(Fmea fmea:fmeaList) {
            ApMonitor apMonitor = apMonitorService.getApMonitor(fmea.getId());
            apMonitorService.insert(apMonitor);
        }
    }
}
