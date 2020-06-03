package com.rb.fmea.service;

import com.rb.fmea.entities.FmeaTeam;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: FmeaTeamService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 16:37
 */
public interface FmeaTeamService {
    /**
     * @Author yyk
     * @Description //TODO 添加小组信息
     * @Date 2020/6/3 16:39
     * @Param
     * @return
     **/
    Result insert(String fmeaTeams);

    /**
     * @Author yyk
     * @Description //TODO 修改小组信息
     * @Date 2020/6/3 16:44
     * @Param [fmeaTeam]
     * @return com.rb.fmea.result.Result
     **/
    Result update(FmeaTeam fmeaTeam);

    /**
     * @Author yyk
     * @Description //TODO 根据id小组信息
     * @Date 2020/6/3 16:46
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    Result delete(String ids);

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询小组信息
     * @Date 2020/6/3 16:47
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    Result selectByFmeaId(int fmeaId);
}
