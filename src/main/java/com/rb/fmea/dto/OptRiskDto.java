package com.rb.fmea.dto;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: OptRiskDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/10 10:04
 */
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptRiskDto {
    private int id;
    private String failAnalysis;
    private int severity;
    private String fmeaMeasureCategory;
    private String fmeaMeasuresDesc;
    private double frequencyDegree;
    private double detectionDegree;
    private String apValue;
    private String filterCode;
    private String chargeName;
    private String finshDate;
    private Integer state;
    private String takeMeasures;
    private String finshTime;
}
