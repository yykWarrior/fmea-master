package com.rb.fmea.entities;

import java.io.Serializable;

public class FmeaTeam implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_team.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_team.team_number_name
     *
     * @mbggenerated
     */
    private String teamNumberName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_team.role
     *
     * @mbggenerated
     */
    private String role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_team.Responsiblity
     *
     * @mbggenerated
     */
    private String responsibility;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fmea_team.fmea_id
     *
     * @mbggenerated
     */
    private Integer fmeaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fmea_team
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_team.id
     *
     * @return the value of fmea_team.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_team.id
     *
     * @param id the value for fmea_team.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_team.team_number_name
     *
     * @return the value of fmea_team.team_number_name
     *
     * @mbggenerated
     */
    public String getTeamNumberName() {
        return teamNumberName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_team.team_number_name
     *
     * @param teamNumberName the value for fmea_team.team_number_name
     *
     * @mbggenerated
     */
    public void setTeamNumberName(String teamNumberName) {
        this.teamNumberName = teamNumberName == null ? null : teamNumberName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_team.role
     *
     * @return the value of fmea_team.role
     *
     * @mbggenerated
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_team.role
     *
     * @param role the value for fmea_team.role
     *
     * @mbggenerated
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_team.fmea_id
     *
     * @return the value of fmea_team.fmea_id
     *
     * @mbggenerated
     */
    public Integer getFmeaId() {
        return fmeaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_team.fmea_id
     *
     * @param fmeaId the value for fmea_team.fmea_id
     *
     * @mbggenerated
     */
    public void setFmeaId(Integer fmeaId) {
        this.fmeaId = fmeaId;
    }


}