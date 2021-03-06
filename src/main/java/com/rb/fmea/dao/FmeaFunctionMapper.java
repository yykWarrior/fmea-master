package com.rb.fmea.dao;

import com.rb.fmea.dto.FmeaFunctionDto;
import com.rb.fmea.entities.FmeaFunction;
import com.rb.fmea.entities.FmeaStructure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FmeaFunctionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_function
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_function
     *
     * @mbggenerated
     */
    int insert(FmeaFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_function
     *
     * @mbggenerated
     */
    FmeaFunction selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_function
     *
     * @mbggenerated
     */
    List<FmeaFunction> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_function
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FmeaFunction record);

    /**
     * @Author yyk
     * @Description //TODO 查询一个结构下的所有功能
     * @Date 2020/5/25 13:22
     * @Param [structureId]
     * @return java.util.List<com.rb.fmea.entities.FmeaStructure>
     **/
    List<FmeaFunctionDto> selectOneStructure(int structureId);

    /**
     * @Author yyk
     * @Description //TODO 查询单个fmea下的所有功能
     * @Date 2020/5/25 13:51
     * @Param [fmeaId]
     * @return java.util.List<com.rb.fmea.entities.FmeaFunction>
     **/
    List<FmeaFunctionDto> selectOneFmea(int fmeaId);

    /**
     * @Author yyk
     * @Description //TODO 删除功能关系,根据上下级id
     * @Date 2020/7/7 17:41
     * @Param [superiorId, nextId]
     * @return void
     **/
    void deleteRelate(int superiorId, int nextId);
}