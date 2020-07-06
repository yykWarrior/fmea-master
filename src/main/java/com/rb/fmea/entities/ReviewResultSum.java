package com.rb.fmea.entities;

import java.io.Serializable;


public class ReviewResultSum implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_result_sum.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_result_sum.review_level
     *
     * @mbggenerated
     */
    private String reviewLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_result_sum.month
     *
     * @mbggenerated
     */
    private Integer month;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_result_sum.fmea_id
     *
     * @mbggenerated
     */
    private Integer fmeaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_result_sum.total_num
     *
     * @mbggenerated
     */
    private Integer totalNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table review_result_sum
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_result_sum.id
     *
     * @return the value of review_result_sum.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_result_sum.id
     *
     * @param id the value for review_result_sum.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_result_sum.review_level
     *
     * @return the value of review_result_sum.review_level
     *
     * @mbggenerated
     */
    public String getReviewLevel() {
        return reviewLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_result_sum.review_level
     *
     * @param reviewLevel the value for review_result_sum.review_level
     *
     * @mbggenerated
     */
    public void setReviewLevel(String reviewLevel) {
        this.reviewLevel = reviewLevel == null ? null : reviewLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_result_sum.month
     *
     * @return the value of review_result_sum.month
     *
     * @mbggenerated
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_result_sum.month
     *
     * @param month the value for review_result_sum.month
     *
     * @mbggenerated
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_result_sum.fmea_id
     *
     * @return the value of review_result_sum.fmea_id
     *
     * @mbggenerated
     */
    public Integer getFmeaId() {
        return fmeaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_result_sum.fmea_id
     *
     * @param fmeaId the value for review_result_sum.fmea_id
     *
     * @mbggenerated
     */
    public void setFmeaId(Integer fmeaId) {
        this.fmeaId = fmeaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_result_sum.total_num
     *
     * @return the value of review_result_sum.total_num
     *
     * @mbggenerated
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_result_sum.total_num
     *
     * @param totalNum the value for review_result_sum.total_num
     *
     * @mbggenerated
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review_result_sum
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", reviewLevel=").append(reviewLevel);
        sb.append(", month=").append(month);
        sb.append(", fmeaId=").append(fmeaId);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public ReviewResultSum(Integer id, String reviewLevel, Integer month, Integer fmeaId, Integer totalNum) {
        this.id = id;
        this.reviewLevel = reviewLevel;
        this.month = month;
        this.fmeaId = fmeaId;
        this.totalNum = totalNum;
    }

    public ReviewResultSum(String reviewLevel, Integer month, Integer fmeaId, Integer totalNum) {
        this.reviewLevel = reviewLevel;
        this.month = month;
        this.fmeaId = fmeaId;
        this.totalNum = totalNum;
    }
}