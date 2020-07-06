package com.rb.fmea.service.impl;

import com.rb.fmea.dao.FmeaApMapper;
import com.rb.fmea.entities.ApPriority;
import com.rb.fmea.entities.FmeaAp;
import com.rb.fmea.entities.FmeaFailAnalysis;
import com.rb.fmea.entities.FmeaMeasures;
import com.rb.fmea.service.ApPriorityService;
import com.rb.fmea.service.FmeaApService;
import com.rb.fmea.service.FmeaFailAnalysisService;
import com.rb.fmea.service.FmeaMeasuresService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version v1.0
 * @ClassName: FmeaApServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/5 13:36
 */
@Service
public class FmeaApServiceImpl implements FmeaApService {
    @Autowired
    private FmeaMeasuresService fmeaMeasuresService;
    @Resource
    private FmeaApMapper fmeaApMapper;
    @Autowired
    private FmeaFailAnalysisService fmeaFailAnalysisService;
    @Autowired
    private ApPriorityService apPriorityService;


    /**
     * @Author yyk
     * @Description //TODO 更新ap值
     * @Date 2020/6/5 13:40
     * @Param [failAnalysisId, fmeaMeasures]
     * @return void
     **/
    @Override
    public String updateAp(int failAnalysisId, FmeaMeasures fmeaMeasures) {
         String apResult="";
        Integer maxSeverity=0;
        Integer optimizeMeasures = fmeaMeasures.getOptimizeMeasures();
        Integer fmeaMeasuresCategory = fmeaMeasures.getFmeaMeasuresCategory();
        //查询严重度
        List<FmeaFailAnalysis> fmeaFailAnalysisList = fmeaFailAnalysisService.selectSeverityByFailAnalysisId(failAnalysisId);
        if(fmeaFailAnalysisList.size()!=0){
            List<Integer> collect = fmeaFailAnalysisList.stream().map(FmeaFailAnalysis::getSeverity).collect(Collectors.toList());
            collect.remove(null);
            maxSeverity = Collections.max(collect);
        }

        //判断是否为优化措施,0非优化,1优化
            //判断是否为探测措施还是预防措施,0预防措施，1探测措施
            if(fmeaMeasuresCategory==0){
                 apResult=selectMeasures(failAnalysisId,fmeaMeasuresCategory,1,optimizeMeasures,maxSeverity);
            }else if(fmeaMeasuresCategory==1){
                 apResult=selectMeasures(failAnalysisId,0,fmeaMeasuresCategory,optimizeMeasures,maxSeverity);
            }
        return apResult;
    }


    public String selectMeasures(int failAnalysisId, int fmeaMeasuresCategory0,int fmeaMeasuresCategory1, int optimizeMeasures,int maxSeverity){
        String apResult="";
        Integer frequencyDegree0=0;
        Integer frequencyDegree1=0;
        //查出所有的预防措施
        List<FmeaMeasures> fmeaFrequencyDegreeMeasuresList = fmeaMeasuresService.selectFmeaMeasuresCategoryAndOptimizeMeasures(fmeaMeasuresCategory0, optimizeMeasures);
        if(fmeaFrequencyDegreeMeasuresList.size()!=0) {
            //计算出最小频度
            List<Integer> frequencyDegreeList0 = fmeaFrequencyDegreeMeasuresList.stream().map(FmeaMeasures::getFrequencyDegree).collect(Collectors.toList());
            //boolean contains = frequencyDegreeList0.contains(null);
            frequencyDegreeList0.remove(null);
            frequencyDegree0 = Collections.min(frequencyDegreeList0);
        }
        //查询出探测措施
        List<FmeaMeasures> fmeaMeasuresList = fmeaMeasuresService.selectFmeaMeasuresCategoryAndOptimizeMeasures(fmeaMeasuresCategory1, optimizeMeasures);
        if(fmeaMeasuresList.size()!=0) {
            //计算出最小探测度
            List<Integer> frequencyDegreeList1 = fmeaMeasuresList.stream().map(FmeaMeasures::getFrequencyDegree).collect(Collectors.toList());
            frequencyDegreeList1.remove(null);
            frequencyDegree1 = Collections.min(frequencyDegreeList1);
        }
        if(optimizeMeasures==0) {
            //更新Ap值
            apResult=updateAp(frequencyDegree0, frequencyDegree1, maxSeverity, failAnalysisId);
        }else if(optimizeMeasures==1){
            apResult=updateProtyAp(frequencyDegree0, frequencyDegree1, maxSeverity, failAnalysisId);
        }
        return apResult;
    }

    //更新Ap值和查询对应Ap值
    public String updateAp(int frequencyDegree, int detectionDegree, int severity,int failAnalysisId){
        //查询Ap表里的ap值
        FmeaAp fmeaAp=fmeaApMapper.selectFailAnalysisId(failAnalysisId);
        //根据严重度，频度，探测度，查询出对应的ap值
        ApPriority apPriority = apPriorityService.selectFrequencyDegreeAnDetectionDegreeAndSeverity(frequencyDegree, detectionDegree, severity);
        if (apPriority != null && !StringUtils.isBlank(apPriority.getApValue())) {
            if (fmeaAp == null) {
                FmeaAp fmeaAp1 = new FmeaAp();
                //查询对应表
                fmeaAp1.setApValue(apPriority.getApValue());
                fmeaAp1.setDetectionDegree(detectionDegree);
                fmeaAp1.setFailAnalysisId(failAnalysisId);
                fmeaAp1.setFrequencyDegree(frequencyDegree);
                fmeaAp1.setSeverity(severity);
                fmeaApMapper.insert(fmeaAp1);
            } else {

                //更新Ap表里的值
                fmeaApMapper.updateByFailAnalysisId(failAnalysisId, apPriority.getApValue(),frequencyDegree,detectionDegree);
            }
        }
        return apPriority==null?"":apPriority.getApValue();
    }



    //更新Ap值和查询对应Ap值
    public String updateProtyAp(int frequencyDegree, int detectionDegree, int severity,int failAnalysisId){
        //查询Ap表里的ap值
        FmeaAp fmeaAp=fmeaApMapper.selectFailAnalysisId(failAnalysisId);
        //根据严重度，频度，探测度，查询出对应的ap值
        ApPriority apPriority = apPriorityService.selectFrequencyDegreeAnDetectionDegreeAndSeverity(frequencyDegree, detectionDegree, severity);
        if (apPriority != null && !StringUtils.isBlank(apPriority.getApValue())) {
            if (fmeaAp == null) {
                FmeaAp fmeaAp1 = new FmeaAp();
                fmeaAp1.setOptApValue(apPriority.getApValue());
                fmeaAp1.setSeverity(severity);
                fmeaAp1.setOptDetectionDegree(detectionDegree);
                fmeaAp1.setOptFrequencyDegree(frequencyDegree);
                fmeaAp1.setFailAnalysisId(failAnalysisId);
            } else {
                //更新Ap表里的值
                fmeaApMapper.updateProtyByFailAnalysisId(failAnalysisId, apPriority.getApValue(),frequencyDegree,detectionDegree);
            }
        }
        return apPriority==null?"":apPriority.getApValue();
    }
}