package com.rb.fmea.dao;

import com.rb.fmea.entities.RolePerm;
import java.util.List;

public interface RolePermMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int insert(RolePerm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    RolePerm selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    List<RolePerm> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RolePerm record);
}