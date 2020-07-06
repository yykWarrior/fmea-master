package com.rb.fmea.entities;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: Document
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/8 16:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Document {
    private int id;
    /**
     *目标状态描述
     **/
    private String targetStatusDescription;
    /**
     *分析范围及新的内容
     **/
    private String scopeContent;
    /**
     *功能分析总结
     **/
    private String functionResult;
    /**
     *高风险失效总结
     **/
    private String highRiskResult;
    /**
     *高风险失效的所采取措施的总结
     **/
    private String highRiskMeasureResult;
    /**
     *优化措施计划
     **/
    private String optPlan;
    /**
     *经验教训总结
     **/
    private String lessonLearned;

    private int fmeaId;
    private String path;
}
