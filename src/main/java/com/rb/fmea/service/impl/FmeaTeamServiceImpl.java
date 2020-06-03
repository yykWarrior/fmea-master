package com.rb.fmea.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rb.fmea.dao.FmeaTeamMapper;
import com.rb.fmea.entities.FmeaTeam;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaTeamService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaTeamServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 16:38
 */
@Service
public class FmeaTeamServiceImpl implements FmeaTeamService {
    @Resource
    private FmeaTeamMapper fmeaTeamMapper;
    /**
     * @Author yyk
     * @Description //TODO 添加小组信息
     * @Date 2020/6/3 16:39
     * @Param [fmeaTeams]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(String fmeaTeams) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(fmeaTeams);
            List<FmeaTeam> fmeaTeamList = JSONObject.parseArray(jsonArray.toJSONString(), FmeaTeam.class);
            for(FmeaTeam fmeaTeam:fmeaTeamList){
                fmeaTeamMapper.insert(fmeaTeam);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改小组信息
     * @Date 2020/6/3 16:44
     * @Param [fmeaTeam]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaTeam fmeaTeam) {

        try {
            fmeaTeamMapper.updateByPrimaryKey(fmeaTeam);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG, e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id小组信息
     * @Date 2020/6/3 16:47
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            String[] idArray = StringProcess.spliteString(ids, ",");
            for(int i=0;i<idArray.length;i++){
                fmeaTeamMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询小组信息
     * @Date 2020/6/3 16:48
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByFmeaId(int fmeaId) {
        try {
            List<FmeaTeam> fmeaTeamList=fmeaTeamMapper.selectByFmeaId(fmeaId);
            return Result.success(fmeaTeamList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }
}
