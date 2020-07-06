package com.rb.fmea.entities;

import java.io.Serializable;

public class ReviewResume implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_resume.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_resume.review_content
     *
     * @mbggenerated
     */
    private String reviewContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_resume.review_result
     *
     * @mbggenerated
     */
    private String reviewResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_resume.fmea_id
     *
     * @mbggenerated
     */
    private String fmeaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_resume.review_date
     *
     * @mbggenerated
     */
    private String reviewDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column review_resume.organizer
     *
     * @mbggenerated
     */
    private String organizer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table review_resume
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_resume.id
     *
     * @return the value of review_resume.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_resume.id
     *
     * @param id the value for review_resume.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_resume.review_content
     *
     * @return the value of review_resume.review_content
     *
     * @mbggenerated
     */
    public String getReviewContent() {
        return reviewContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_resume.review_content
     *
     * @param reviewContent the value for review_resume.review_content
     *
     * @mbggenerated
     */
    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent == null ? null : reviewContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_resume.review_result
     *
     * @return the value of review_resume.review_result
     *
     * @mbggenerated
     */
    public String getReviewResult() {
        return reviewResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_resume.review_result
     *
     * @param reviewResult the value for review_resume.review_result
     *
     * @mbggenerated
     */
    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult == null ? null : reviewResult.trim();
    }

    public String getFmeaName() {
        return fmeaName;
    }

    public void setFmeaName(String fmeaName) {
        this.fmeaName = fmeaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_resume.review_date
     *
     * @return the value of review_resume.review_date
     *
     * @mbggenerated
     */
    public String getReviewDate() {
        return reviewDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_resume.review_date
     *
     * @param reviewDate the value for review_resume.review_date
     *
     * @mbggenerated
     */
    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate == null ? null : reviewDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column review_resume.organizer
     *
     * @return the value of review_resume.organizer
     *
     * @mbggenerated
     */
    public String getOrganizer() {
        return organizer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column review_resume.organizer
     *
     * @param organizer the value for review_resume.organizer
     *
     * @mbggenerated
     */
    public void setOrganizer(String organizer) {
        this.organizer = organizer == null ? null : organizer.trim();
    }


}