package com.rb.fmea.dto;

import com.rb.fmea.entities.FmeaStructure;
import lombok.*;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FunctionRelateDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/26 9:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FunctionRelateDto {
    private int structureId;

    private List<FmeaStructureDto> parentFmeaFunctionList;

    private int functionId;

    private String functionDesc;

    private String functionRequire;

    private List<FmeaStructureDto> nextFmeaFunctionList;
}
