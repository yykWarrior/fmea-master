package com.rb.fmea.dto;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: OptMeasureDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/19 14:28
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptMeasureDto {
    private String id;
    //fmea
    private String fmeaName;
    //客户
    private String customer;
    //总成
    private String assfembly;
    //零件
    private String part;
    //特性
    private String feature;
    //功能
    private String function;
    //失效
    private String failAnalysis;

    //措施类型
    private String fmeaMeasureCategory;
    //措施描述
    private String fmeaMeasuresDesc;
    //频度
    private double frequencyDegree;
    //探测度
    private double detectionDegree;
    private String filterCode;
    //负责人姓名
    private String chargeName;
    //目标完成日期
    private String finshDate;
    //状态(开放/完成/不执行)
    private String state;
    //采取相应的措施
    private String takeMeasures;
    //完成时间
    private String finshTime;
}
