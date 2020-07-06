package com.rb.fmea.dto;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: MeasureMonitorDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/20 15:21
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasureMonitorDto {

    private int id;
    //开放
    private int open;
    //完成
    private int complete;
    //不实施
    private int noimplent;
    //措施总数
    private int measureTotal;
    //完成率
    private double completion;
    private int mon;
}
