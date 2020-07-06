package com.rb.fmea.dao;

import com.rb.fmea.dto.ApHvalueDto;
import com.rb.fmea.entities.FmeaAp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FmeaApMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_ap
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_ap
     *
     * @mbggenerated
     */
    int insert(FmeaAp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_ap
     *
     * @mbggenerated
     */
    FmeaAp selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_ap
     *
     * @mbggenerated
     */
    List<FmeaAp> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fmea_ap
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FmeaAp record);

    /**
     * @Author yyk
     * @Description //TODO 根据失效分析id查询
     * @Date 2020/6/5 14:15
     * @Param [failAnalysisId]
     * @return com.rb.fmea.entities.FmeaAp
     **/
    FmeaAp selectFailAnalysisId(int failAnalysisId);

    /**
     * @Author yyk
     * @Description //TODO 根据失效分析id更新ap值
     * @Date 2020/6/5 15:11
     * @Param [failAnalysisId, apValue]
     * @return void
     **/
    void updateByFailAnalysisId(@Param("failAnalysisId") int failAnalysisId, @Param("apValue") String apValue, @Param("frequencyDegree") int frequencyDegree, @Param("detectionDegree") int detectionDegree);


    /**
     * @Author yyk
     * @Description //TODO 根据失效分析id优化后更新ap值
     * @Date 2020/6/5 15:11
     * @Param [failAnalysisId, apValue]
     * @return void
     **/
    void updateProtyByFailAnalysisId(@Param("failAnalysisId") int failAnalysisId, @Param("apPriorityValue") String apValue, @Param("optFrequencyDegree") int frequencyDegree, @Param("optDetectionDegree") int detectionDegree);

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询高风险fmea
     * @Date 2020/6/16 17:34
     * @Param [fmeaId]
     * @return java.util.List<com.rb.fmea.dto.ApHvalueDto>
     **/
    List<ApHvalueDto> selectApValueByFmeaId(@Param("fmeaId") int fmeaId, @Param("limit") int limit, @Param("page") int page);

    /**
     * @Author yyk
     * @Description //TODO 查询一个fmea下高风险的总条数
     * @Date 2020/6/17 7:58
     * @Param [fmeaId]
     * @return int
     **/
    int count(int fmeaId);
}