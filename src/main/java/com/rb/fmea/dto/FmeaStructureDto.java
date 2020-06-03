package com.rb.fmea.dto;


import lombok.*;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: fmeaStructureDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 16:59
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FmeaStructureDto {

    private Integer id;


    private String structureName;


    private String structureDesc;

    private Integer fmeaId;

    private List<FmeaStructureDto> fmeaStructureDtoList;

    private List<StructureFunctionDto> fmeaFunctionDtoList;

    private List<FmeaFunctionDto> fmeaFunctionList;
}
