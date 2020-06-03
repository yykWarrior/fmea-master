package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaBasicInformation;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaBasicInformationService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 15:12
 */
public interface FmeaBasicInformationService {
    /**
     * @Author yyk
     * @Description //TODO 插入fmea的基础信息
     * @Date 2020/6/3 15:13
     * @Param [fmeaBasicInformation]
     * @return com.rb.fmea.result.Result
     **/
    Result insert(FmeaBasicInformation fmeaBasicInformation);

    /**
     * @Author yyk
     * @Description //TODO 修改fmea的基础信息
     * @Date 2020/6/3 15:20
     * @Param [fmeaBasicInformation]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaBasicInformation fmeaBasicInformation);

    /**
     * @Author yyk
     * @Description //TODO 根据id删除fmea信息
     * @Date 2020/6/3 15:23
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(int id);

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询对应的基础信息
     * @Date 2020/6/3 15:28
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByFmeaId(int fmeaId);
}
