package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaMeasures;

/**
 * @version v1.0
 * @ClassName: FmeaApService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/5 13:35
 */
public interface FmeaApService {

    String updateAp(int failAnalysisId, FmeaMeasures fmeaMeasures);
}
