package com.rb.fmea.dto;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @version v1.0
 * @ClassName: ApHvalueDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/16 11:14
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//设置链式
@Accessors(chain = true)
public class ApHvalueDto {
    private String part;
    private String feature;
    private String failAnalysis;
    private String apValue;
    private int fmeaId;
}
