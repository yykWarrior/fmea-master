package com.rb.fmea.dto;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: StructureFunctionDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/26 16:50
 */
@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class StructureFunctionDto {
    private int id;
    private String integrate;
    private String functionRequire;
    private int structureId;
    private String structureName;
    private String structureDesc;

}
