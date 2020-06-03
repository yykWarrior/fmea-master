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
    private String responsiblity;

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

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fmea_team.Responsiblity
     *
     * @return the value of fmea_team.Responsiblity
     *
     * @mbggenerated
     */
    public String getResponsiblity() {
        return responsiblity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fmea_team.Responsiblity
     *
     * @param responsiblity the value for fmea_team.Responsiblity
     *
     * @mbggenerated
     */
    public void setResponsiblity(String responsiblity) {
        this.responsiblity = responsiblity == null ? null : responsiblity.trim();
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

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_team
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
        sb.append(", teamNumberName=").append(teamNumberName);
        sb.append(", role=").append(role);
        sb.append(", responsiblity=").append(responsiblity);
        sb.append(", fmeaId=").append(fmeaId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}