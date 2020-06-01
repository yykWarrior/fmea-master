package com.rb.fmea.dto;


import com.rb.fmea.entities.Fmea;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 14:19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FmeaDto  {
    private int id;

    private String fmeaName;


    private String fmeaDesc;


    private Integer productId;

    private String productName;

    private List<FmeaDto> familyFmeaList;

}
