package com.rb.fmea.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rb.fmea.dao.FmeaMeasuresMapper;
import com.rb.fmea.entities.FmeaMeasures;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FmeaMeasuresService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: FmeaMeasuresServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/2 9:41
 */
@Service
public class FmeaMeasuresServiceImpl implements FmeaMeasuresService {
    @Resource
    private FmeaMeasuresMapper fmeaMeasuresMapper;
    /**
     * @Author yyk
     * @Description //TODO 批量插入一个失效分析的措施
     * @Date 2020/6/2 9:42
     * @Param [measures]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(String measures, int failAnalysisId, int category, int optimize) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(measures);
            List<FmeaMeasures> fmeaMeasuresList = JSONObject.parseArray(jsonArray.toJSONString(), FmeaMeasures.class);
            for(FmeaMeasures fmeaMeasures:fmeaMeasuresList){
                fmeaMeasures.setFmeaFailAnalysisId(failAnalysisId);
                fmeaMeasures.setFmeaMeasuresCategory(category);
                fmeaMeasures.setOptimizeMeasures(optimize);
                fmeaMeasuresMapper.insert(fmeaMeasures);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 删除措施
     * @Date 2020/6/2 10:52
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            String s = StringProcess.determinesWhetherStringEndsInComma(ids);
            String[] idArray = StringProcess.spliteString(s, ",");
            for(String id:idArray){
                fmeaMeasuresMapper.deleteByPrimaryKey(Integer.parseInt(id));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改措施
     * @Date 2020/6/2 11:02
     * @Param [fmeaMeasures]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FmeaMeasures fmeaMeasures) {
        try {
            //查询原来的数据
            FmeaMeasures fmeaMeasures1 = fmeaMeasuresMapper.selectByPrimaryKey(fmeaMeasures.getId());
            fmeaMeasuresMapper.updateByPrimaryKey(fmeaMeasures);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }
}
