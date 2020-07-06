package com.rb.fmea.service;

import com.rb.fmea.entities.Fmea;
import com.rb.fmea.result.Result;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 10:20
 */
public interface FmeaService {
    /**
     * @Author yyk
     * @Description //TODO 添加fmea
     * @Date 2020/5/21 10:21
     * @Param [fmea]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(Fmea fmea);

    /**
     * @Author yyk
     * @Description //TODO 查询所有基础fmea
     * @Date 2020/5/21 10:27
     * @Param []
     * @return com.rb.fmea.result.Result
     **/
    Result selectBasicFmea();

    /**
     * @Author yyk
     * @Description //TODO 修改fmea信息
     * @Date 2020/5/21 10:56
     * @Param [fmea]
     * @return com.rb.fmea.result.Result
     **/
    Result update(Fmea fmea);

    /**
     * @Author yyk
     * @Description //TODO 删除fmea
     * @Date 2020/5/21 11:01
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(String ids);

    /**
     * @Author yyk
     * @Description //TODO 查询一个产品下对应的基础,家族,新品fmea
     * @Date 2020/5/21 11:20
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByProductId(int productId);

    /**
     * @Author yyk
     * @Description //TODO 查询所有fmea
     * @Date 2020/6/16 9:52
     * @Param []
     * @return java.util.List<com.rb.fmea.entities.Fmea>
     **/
    List<Fmea> selectAll();

    Result updateState(int fmeaId);

    Boolean selectByIdReturnState(int fmeaId);
}
