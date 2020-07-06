package com.rb.fmea.entities;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: ApPrioority
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/5 13:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApPriority {
    private int id;
    private int frequencyDegree;
    private int detectionDegree;
    private int severity;
    private String apValue;
}
