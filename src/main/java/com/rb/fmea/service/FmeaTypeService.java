package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaType;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;

/**
 * @version v1.0
 * @ClassName: FmeaTypeService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/19 10:09
 */
public interface FmeaTypeService {
    /**
     * @Author yyk
     * @Description //TODO 分页查询
     * @Date 2020/6/19 10:13
     * @Param [name, pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    ResultDto selectPage(String name, PageParameter pageParameter);

    /**
     * @Author yyk
     * @Description //TODO 修改
     * @Date 2020/6/19 10:13
     * @Param [fmeaType]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaType fmeaType);

    /**
     * @Author yyk
     * @Description //TODO 查询所有
     * @Date 2020/6/19 10:32
     * @Param []
     * @return com.rb.fmea.result.ResultDto
     **/
    Result selectAll();

    /**
     * @Author yyk
     * @Description //TODO 删除
     * @Date 2020/6/19 10:37
     * @Param []
     * @return com.rb.fmea.result.Result
     *
     * @param ids*/
    Result delete(String ids);

    /**
     * @Author yyk
     * @Description //TODO 增加
     * @Date 2020/6/19 10:41
     * @Param [fmeaType]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(FmeaType fmeaType);

    /**
     * @Author yyk
     * @Description //TODO 在产品下添加类型/方向
     * @Date 2020/6/19 10:48
     * @Param [productId, typeId]
     * @return com.rb.fmea.result.Result
     **/
    Result insetByProduct(int productId, String typeId);

    /**
     * @Author yyk
     * @Description //TODO 在产品下查询
     * @Date 2020/6/19 10:55
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByProduct(int productId);

    /**
     * @Author yyk
     * @Description //TODO 在产品下删除
     * @Date 2020/6/19 11:01
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result deleteByProduct(String ids);
}
