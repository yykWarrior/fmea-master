package com.rb.fmea.entities;

import java.io.Serializable;

public class UserLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_log.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_log.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_log.ip
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_log.login_date
     *
     * @mbggenerated
     */
    private String loginDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_log
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_log.id
     *
     * @return the value of user_log.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_log.id
     *
     * @param id the value for user_log.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_log.user_name
     *
     * @return the value of user_log.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_log.user_name
     *
     * @param userName the value for user_log.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_log.ip
     *
     * @return the value of user_log.ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_log.ip
     *
     * @param ip the value for user_log.ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_log.login_date
     *
     * @return the value of user_log.login_date
     *
     * @mbggenerated
     */
    public String getLoginDate() {
        return loginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_log.login_date
     *
     * @param loginDate the value for user_log.login_date
     *
     * @mbggenerated
     */
    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate == null ? null : loginDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_log
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
        sb.append(", userName=").append(userName);
        sb.append(", ip=").append(ip);
        sb.append(", loginDate=").append(loginDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}