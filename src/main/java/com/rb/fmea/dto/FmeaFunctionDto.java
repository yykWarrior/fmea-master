package com.rb.fmea.dto;

import com.rb.fmea.entities.FmeaFailAnalysis;
import lombok.*;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaFunctionDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/26 9:34
 */

@Data
@Setter
@Getter
@NoArgsConstructor
public class FmeaFunctionDto {
    private Integer id;

    private String functionDesc;

    private String functionRequire;

    private Integer fmeaStructureId;

    private List<FmeaFailAnalysis> fmeaFailAnalysisList;

}
