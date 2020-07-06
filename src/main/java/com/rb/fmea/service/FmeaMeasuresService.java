package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaMeasures;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaMeasuresService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/2 9:41
 */
public interface FmeaMeasuresService {
    /**
     * @Author yyk
     * @Description //TODO 批量插入一个失效分析的措施
     * @Date 2020/6/2 9:41
     * @Param [measures]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(String measures, int failAnalysisId, int category, int optimize);

    /**
     * @Author yyk
     * @Description //TODO 删除措施
     * @Date 2020/6/2 10:51
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(String ids, int fmeaId);

    /**
     * @Author yyk
     * @Description //TODO 修改措施
     * @Date 2020/6/2 11:02
     * @Param [fmeaMeasures]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaMeasures fmeaMeasures, int fmeaId);


    /**
     * @Author yyk
     * @Description //TODO 根据是否优化，和措施类别查询
     * @Date 2020/6/5 13:57
     * @Param [fmeaMeasuresCategory, optimizeMeasures]
     * @return java.util.List<com.rb.fmea.entities.FmeaMeasures>
     **/
    List<FmeaMeasures> selectFmeaMeasuresCategoryAndOptimizeMeasures(int fmeaMeasuresCategory,int optimizeMeasures);

    /**
     * @Author yyk
     * @Description //TODO 根据结构id查询对应功能下的失效分析下的措施
     * @Date 2020/6/9 13:29
     * @Param [structureId]
     * @return com.rb.fmea.result.ResultDto
     **/
    ResultDto selectByStructureId(int structureId, int page, int limit);

    /**
     * @Author yyk
     * @Description //TODO 根据结构id查询对应功能下的失效分析下的优化措施
     * @Date 2020/6/10 9:49
     * @Param [structureId, page, limit]
     * @return com.rb.fmea.result.ResultDto
     **/
    ResultDto selectByStructureIdAndOpt(int structureId, int page, int limit);

    /**
     * @Author yyk
     * @Description //TODO 一个产品下的优化措施监控
     * @Date 2020/6/19 16:46
     * @Param [productId, pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    ResultDto selectOptMeasureOneProduct(int productId, PageParameter pageParameter);

    /**
     * @Author yyk
     * @Description //TODO 修改不执行状态
     * @Date 2020/6/20 9:06
     * @Param [measureId]
     * @return com.rb.fmea.result.ResultDto
     **/
    Result updateState(int measureId);

    /**
     * @Author yyk
     * @Description //TODO 点击完成，改变状态
     * @Date 2020/6/20 10:33
     * @Param [multipartFile]
     * @return com.rb.fmea.result.Result
     **/
    Result updateStateFinsh(MultipartFile multipartFile, int measureId);
}
