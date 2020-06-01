package com.rb.fmea.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rb.fmea.dao.FunctionStandardMapper;
import com.rb.fmea.entities.FunctionStandard;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.FunctionStandardService;
import com.rb.fmea.util.StringProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: FunctionStandardServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/23 13:49
 */
@Service
public class FunctionStandardServiceImpl implements FunctionStandardService {
    @Resource
    private FunctionStandardMapper functionStandardMapper;
    /**
     * @Author yyk
     * @Description //TODO 批量插入标准
     * @Date 2020/5/23 13:50
     * @Param [functionStandards]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result insert(String functionStandards) {
        try {
            JSONArray jsonArray = JSONArray.parseArray(functionStandards);
            List<FunctionStandard> list = JSONObject.parseArray(jsonArray.toJSONString(), FunctionStandard.class);
            for(FunctionStandard functionStandard:list){
                //设置动名词和描述拼接
                String integrate=functionStandard.getVerb()+functionStandard.getMoun()+functionStandard.getDesc();
                functionStandard.setIntegrate(integrate);
                functionStandardMapper.insert(functionStandard);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }

    /**
     * @Author yyk
     * @Description //TODO 删除标准信息
     * @Date 2020/5/23 14:17
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result delete(String ids) {
        try {
            String[] idArray = StringProcess.spliteString(ids, ",");
            for(int i=0;i<idArray.length;i++) {
                functionStandardMapper.deleteByPrimaryKey(Integer.parseInt(idArray[i]));
            }
            return Result.success();
        } catch (NumberFormatException e) {
            return Result.error(new CodeMsg(ReturnCode.DELETE_IS_ERROR,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 修改标准
     * @Date 2020/5/23 14:22
     * @Param [functionStandard]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result update(FunctionStandard functionStandard) {
        try {
            functionStandardMapper.updateByPrimaryKey(functionStandard);
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据类型查询下面所有的标准
     * @Date 2020/5/23 14:25
     * @Param [type]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public Result selectAllByType(int type) {
        try {
            List<FunctionStandard> functionStandards = functionStandardMapper.selectAll(type);
            return Result.success(functionStandards);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 多条件分页查询
     * @Date 2020/5/23 14:36
     * @Param [pageParameter, functionStandard]
     * @return com.rb.fmea.result.ResultDto
     **/
    @Override
    public ResultDto select(PageParameter pageParameter, FunctionStandard functionStandard) {
        try {
            int page=(pageParameter.getPage()-1)*pageParameter.getLimit();
            functionStandard.setVerb(StringProcess.stringConcate(functionStandard.getVerb(), "%"));
            functionStandard.setMoun(StringProcess.stringConcate(functionStandard.getMoun(), "%"));
            functionStandard.setDesc(StringProcess.stringConcate(functionStandard.getDesc(), "%"));
            functionStandard.setIntegrate(StringProcess.stringConcate(functionStandard.getIntegrate(), "%"));
            //查询数据
            List<FunctionStandard> functionStandardList=functionStandardMapper.select(page,pageParameter.getLimit(),functionStandard);
            //查询条数
            int count=functionStandardMapper.count(functionStandard);
            return new ResultDto(0,"",count,functionStandardList);
        } catch (Exception e) {
            return new ResultDto(1,e.getMessage());
        }
    }

    /**
     * @Author yyk
     * @Description //TODO 根据拼接字段模糊查询
     * @Date 2020/5/29 14:13
     * @Param [integrate]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result selectByIntegrateLike(String integrate) {
        try {
            integrate = StringProcess.stringConcate(integrate, "%");
            List<FunctionStandard> functionStandardList=functionStandardMapper.selectByIntegrateLike(integrate);
            return Result.success(functionStandardList);
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,e.getMessage()));
        }

    }
}
