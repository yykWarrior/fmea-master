package com.rb.fmea.dto;

import lombok.*;

/**
 * @version v1.0
 * @ClassName: ReviewResultDto
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/23 17:13
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResultDto {

    private Integer id;

   /* *//**
     *0表示待建，1表示计划，2表示完成，3表示待建超期，4表示计划超期
     *//*
    private Integer functionReviewResult;

    *//**
     *0表示待建，1表示计划，2表示完成，3表示待建超期，4表示计划超期
     *//*
    private Integer failAnalysisReviewResult;

    *//**
     *0表示待建，1表示计划，2表示完成，3表示待建超期，4表示计划超期
     *//*
    private Integer riskReviewResult;*/


    private String  fmeaName;

    private String structName;

    private int reviewSate;
}
