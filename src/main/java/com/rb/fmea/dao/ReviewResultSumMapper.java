package com.rb.fmea.dao;

import com.rb.fmea.entities.ReviewResultSum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReviewResultSumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_result_sum
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_result_sum
     *
     * @mbggenerated
     */
    int insert(ReviewResultSum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_result_sum
     *
     * @mbggenerated
     */
    ReviewResultSum selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_result_sum
     *
     * @mbggenerated
     */
    List<ReviewResultSum> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_result_sum
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ReviewResultSum record);
/**
 * @Author yyk
 * @Description //TODO 根据产品id查询
 * @Date 2020/6/24 17:26
 * @Param [productId]
 * @return java.util.List<com.rb.fmea.entities.ReviewResultSum>
 **/
    List<ReviewResultSum> selectByProductId(int productId);
}