package com.rb.fmea.dao;

import com.rb.fmea.entities.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReviewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbggenerated
     */
    int insert(Review record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbggenerated
     */
    Review selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbggenerated
     */
    List<Review> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Review record);

    List<Review> selectByPage(int page, int limit, int productId);

    int count(int productId);

    Review selectByFmeaIdAndStep(int fmeaId, int structId);

    List<Review> selectByReviewState(int reviewSate);
}