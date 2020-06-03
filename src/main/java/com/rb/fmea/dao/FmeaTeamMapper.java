package com.rb.fmea.dao;

import com.rb.fmea.entities.FmeaTeam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FmeaTeamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_team
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_team
     *
     * @mbggenerated
     */
    int insert(FmeaTeam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_team
     *
     * @mbggenerated
     */
    FmeaTeam selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_team
     *
     * @mbggenerated
     */
    List<FmeaTeam> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_team
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FmeaTeam record);

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询小组信息
     * @Date 2020/6/3 16:48
     * @Param [fmeaId]
     * @return java.util.List<com.rb.fmea.entities.FmeaTeam>
     **/
    List<FmeaTeam> selectByFmeaId(int fmeaId);
}