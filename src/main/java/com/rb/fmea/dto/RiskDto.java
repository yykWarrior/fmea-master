package com.rb.fmea.dto;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: RiskDto
 * @Description: TODO 风险评估封装
 * @Author: yyk
 * @Date: 2020/6/9 16:18
 */
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskDto {
    private int id;
    private String failAnalysis;
    private int severity;
    private String fmeaMeasureCategory;
    private String fmeaMeasuresDesc;
    private double frequencyDegree;
    private double detectionDegree;
    private String apValue;
    private String filterCode;


}
