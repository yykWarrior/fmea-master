package com.rb.fmea.dao;

import com.rb.fmea.dto.FmeaStructureDto;
import com.rb.fmea.entities.FmeaStructure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FmeaStructureMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_structure
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_structure
     *
     * @mbggenerated
     */
    int insert(FmeaStructure record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_structure
     *
     * @mbggenerated
     */
    FmeaStructure selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_structure
     *
     * @mbggenerated
     */
    List<FmeaStructure> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_structure
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FmeaStructure record);

    /**
     * @Author yyk
     * @Description //TODO 根据上一级id查询
     * @Date 2020/5/21 16:38
     * @Param [assemblyId]
     * @return com.rb.fmea.entities.FmeaStructure
     **/
    FmeaStructure selectBySuperiorId(int assemblyId);

    /**
     * @Author yyk
     * @Description //TODO 根据上级id和fmeaid查询
     * @Date 2020/5/21 17:06
     * @Param [parentId, fmeaId]
     * @return java.util.List<com.rb.fmea.dto.FmeaStructureDto>
     **/
    List<FmeaStructureDto> selectBySuperiorIdAndFmeaId(@Param("parentId") int parentId, @Param("fmeaId") int fmeaId);

    /**
     * @Author yyk
     * @Description //TODO
     * @Date 2020/5/22 10:33
     * @Param []
     * @return void
     *
     * @param structureName
     * @param structureDesc*/
    void updateStructureById(@Param("id") int id, @Param("structureName") String structureName, @Param("structureDesc") String structureDesc);

    /**
     * @Author yyk
     * @Description //TODO 根据id删除结构下有子集同时删除
     * @Date 2020/5/23 9:20
     * @Param [id]
     * @return void
     **/
    void delete(int id);
}