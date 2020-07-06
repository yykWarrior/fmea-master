package com.rb.fmea.dao;

import com.rb.fmea.entities.ReviewResume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ReviewResumeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_resume
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_resume
     *
     * @mbggenerated
     */
    int insert(ReviewResume record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_resume
     *
     * @mbggenerated
     */
    ReviewResume selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_resume
     *
     * @mbggenerated
     */
    List<ReviewResume> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_resume
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ReviewResume record);

    void updateById(@Param("id") int id, @Param("reviewResult") String reviewResult);

    List<ReviewResume> selectLikeFmeaName(@Param("fmeaName") String fmeaName, @Param("page") int page, @Param("limit") int limit);

    int count(String fmeaName);
}