package com.rb.fmea.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rb.fmea.dao.FmeaBenchmarkPlanMapper;
import com.rb.fmea.entities.FmeaBenchmarkPlan;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaBenchmarkPlanService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaBenchmarkPlanServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/3 17:37
 */
@Service
public class FmeaBenchmarkPlanServiceImpl implements FmeaBenchmarkPlanService {
    @Resource
    private FmeaBenchmarkPlanMapper fmeaBenchmarkPlanMapper;
    /**
     * @Author yyk
     * @Description //TODO 插入fmea的基准计划
     * @Date 2020/6/4 8:43
     * @Param [fmeaBenchmarkPlan]
     * @return com.rb.fmea.result.Result
     *
     * @param fmeaBenchmarkPlan*/
    @Override
    public Result insert(String fmeaBenchmarkPlan) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(fmeaBenchmarkPlan);
            List<FmeaBenchmarkPlan> fmeaBenchmarkPlanList = JSONObject.parseArray(jsonArray.toJSONString(), FmeaBenchmarkPlan.class);
            for(FmeaBenchmarkPlan fmeaBenchmarkPlan1:fmeaBenchmarkPlanList) {
                fmeaBenchmarkPlanMapper.insert(fmeaBenchmarkPlan1);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改基准计划
     * @Date 2020/6/4 15:19
     * @Param [fmeaBenchmarkPlan]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaBenchmarkPlan fmeaBenchmarkPlan) {
        try {
            fmeaBenchmarkPlanMapper.updateByPrimaryKey(fmeaBenchmarkPlan);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id删除基准计划
     * @Date 2020/6/4 15:19
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            String[] idArray = StringProcess.spliteString(ids, ",");
            for(int i=0;i<idArray.length;i++){
                fmeaBenchmarkPlanMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据fmeaid查询
     * @Date 2020/6/4 15:20
     * @Param [fmeaId]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByFmeaId(int fmeaId) {
        try {
            List<FmeaBenchmarkPlan> fmeaBenchmarkPlanList=fmeaBenchmarkPlanMapper.selectByFmeaId(fmeaId);
            return Result.success(fmeaBenchmarkPlanList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }
}
