package com.rb.fmea.scheduled;

import com.rb.fmea.dao.FmeaFailAnalysisMapper;
import com.rb.fmea.dao.FmeaFunctionMapper;
import com.rb.fmea.dao.FmeaStructureMapper;
import com.rb.fmea.dto.FmeaFunctionDto;
import com.rb.fmea.entities.FmeaFailAnalysis;
import com.rb.fmea.entities.FmeaStructure;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: DeleteSchedule
 * @Description: TODO 定时删除已删除的fmea的相关数据(中午12点)
 * @Author: yyk
 * @Date: 2020/7/7 16:05
 */
@Component
public class DeleteSchedule {
    @Resource
    private FmeaStructureMapper fmeaStructureMapper;
    @Resource
    private FmeaFunctionMapper fmeaFunctionMapper;
    @Resource
    private FmeaFailAnalysisMapper fmeaFailAnalysisMapper;

    @Scheduled(cron = "0 0 12 * * ?")
    public void delete(){
        deleteAll();
    }


    public void deleteAll(){
        //删除结构
        //查询出所有的结构
        List<FmeaStructure> fmeaStructureList=fmeaStructureMapper.selectAllFmeaIdIsNull();
        for(FmeaStructure fmeaStructure:fmeaStructureList){
            List<FmeaFunctionDto> fmeaFunctionDtoList = fmeaFunctionMapper.selectOneStructure(fmeaStructure.getId());
            //删除功能关系表中数据
            //查询出功能对应下的失效分析
            for(FmeaFunctionDto fmeaFunctionDto:fmeaFunctionDtoList){
                List<FmeaFailAnalysis> fmeaFailAnalysisList = fmeaFailAnalysisMapper.selectByFailAnalysisId(fmeaFunctionDto.getId());
                for(FmeaFailAnalysis fmeaFailAnalysis:fmeaFailAnalysisList){
                    deleteFmeaFailAnalysis(fmeaFailAnalysis);
                }
            }
        }
    }

    private void deleteFmeaFailAnalysis(FmeaFailAnalysis fmeaFailAnalysis) {
        //删除失效关系表

    }
}
