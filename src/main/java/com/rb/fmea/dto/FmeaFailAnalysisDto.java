package com.rb.fmea.dto;

import com.rb.fmea.entities.FmeaFailAnalysis;
import com.rb.fmea.entities.FmeaFunction;
import com.rb.fmea.entities.FmeaStructure;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaFailAnalysisDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/1 10:35
 */
@Setter
@Getter
@NoArgsConstructor
@Data
public class FmeaFailAnalysisDto {
    private FmeaStructure fmeaStructure;
    private FmeaFunction fmeaFunction;
    private FmeaFailAnalysis fmeaFailAnalysis;
    private List<FmeaStructureDto> parentFmeaStructureDtoList;
    private List<FmeaStructureDto> nextFmeaStructureDtoList;
}
