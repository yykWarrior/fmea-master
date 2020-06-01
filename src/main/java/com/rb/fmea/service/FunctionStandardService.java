package com.rb.fmea.service;

import com.rb.fmea.entities.FunctionStandard;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;

/**
 * @version v1.0
 * @ClassName: FunctionStandardService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/23 13:49
 */
public interface FunctionStandardService {
    /**
     * @Author yyk
     * @Description //TODO 批量插入标准
     * @Date 2020/5/23 13:50
     * @Param [functionStandards]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(String functionStandards);

    /**
     * @Author yyk
     * @Description //TODO 删除标准信息
     * @Date 2020/5/23 14:17
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(String ids);

    /**
     * @Author yyk
     * @Description //TODO 修改标准
     * @Date 2020/5/23 14:21
     * @Param [functionStandard]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FunctionStandard functionStandard);

    /**
     * @Author yyk
     * @Description //TODO 根据类型查询下面所有的标准
     * @Date 2020/5/23 14:25
     * @Param [type]
     * @return com.rb.fmea.result.ResultDto
     **/
    Result selectAllByType(int type);

    /**
     * @Author yyk
     * @Description //TODO 多添件分页查询
     * @Date 2020/5/23 14:35
     * @Param [pageParameter, functionStandard]
     * @return com.rb.fmea.result.ResultDto
     **/
    ResultDto select(PageParameter pageParameter, FunctionStandard functionStandard);

    /**
     * @Author yyk
     * @Description //TODO 根据拼接字段模糊查询
     * @Date 2020/5/29 14:12
     * @Param [integrate]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByIntegrateLike(String integrate);
}
