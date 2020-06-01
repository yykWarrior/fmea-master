package com.rb.fmea.controller;


import com.rb.fmea.entities.FunctionStandard;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.FunctionStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * @version v1.0
 * @ClassName: FunctionStandardController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/23 13:12
 */
@RestController
@Api(description = "标准化表接口")
public class FunctionStandardController {
    @Autowired
    private FunctionStandardService functionStandardService;


    /**
     * @Author yyk
     * @Description //TODO 批量插入标准化表
     * @Date 2020/5/23 13:18
     * @Param [functionStandard]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "批量插入标准数据",notes = "批量插入标准数据")
    @RequestMapping(value = "functionStandard/insert",method = RequestMethod.POST)
    public Result insert(String functionStandards){
        return functionStandardService.insert(functionStandards);
    }

    /**
     * @Author yyk
     * @Description //TODO 删除标准表信息
     * @Date 2020/5/23 14:15
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除标准数据",notes = "删除标准数据")
    @RequestMapping(value = "functionStandard/delete/{ids}",method = RequestMethod.GET)
    public Result delete(@PathVariable("ids") String ids){
        return functionStandardService.delete(ids);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据id修改标准
     * @Date 2020/5/23 14:21
     * @Param [functionStandard]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改标准数据",notes = "修改标准数据")
    @RequestMapping(value = "functionStandard/update",method = RequestMethod.POST)
    public Result update(FunctionStandard functionStandard){
        return functionStandardService.update(functionStandard);
    }

    /**
     * @Author yyk
     * @Description //TODO
     * @Date 2020/5/23 14:23
     * @Param []
     * @return com.rb.fmea.result.ResultDto
     **/
    @ApiOperation(value = "根据类型查询所有标准",notes = "根据类型查询所有标准")
    @RequestMapping(value = "functionStandard/selectAllByType",method = RequestMethod.GET)
    public Result selectAllByType(int type){
        return functionStandardService.selectAllByType(type);
    }

    /**
     * @Author yyk
     * @Description //TODO 分页查询标准
     * @Date 2020/5/23 14:31
     * @Param [pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    @ApiOperation(value = "分页查询所有标准",notes = "分页查询所有标准")
    @RequestMapping(value = "functionStandard/select",method = RequestMethod.GET)
    public ResultDto select(PageParameter pageParameter,FunctionStandard functionStandard){
        return functionStandardService.select(pageParameter,functionStandard);
    }

    /**
     * @Author yyk
     * @Description //TODO 根据拼接字段模糊查询
     * @Date 2020/5/29 14:11
     * @Param [Integrate]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "拼接字段模糊查询",notes = "拼接字段模糊查询")
    @RequestMapping(value = "functionStandard/selectByIntegrateLike",method = RequestMethod.POST)
    public Result selectByIntegrateLike(String integrate){
        return functionStandardService.selectByIntegrateLike(integrate);
    }

}
