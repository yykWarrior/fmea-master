package com.rb.fmea.controller;

import com.rb.fmea.entities.FmeaType;
import com.rb.fmea.page.PageParameter;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ResultDto;
import com.rb.fmea.service.FmeaTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ClassName: FmeaTypeController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/6/19 10:07
 */
@Api(description = "fmea类型或方向接口")
@RestController
public class FmeaTypeController {
    @Autowired
    private FmeaTypeService fmeaTypeService;

    /**
     * @Author yyk
     * @Description //TODO 修改
     * @Date 2020/6/19 10:10
     * @Param [fmeaType]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改",notes = "修改")
    @RequestMapping(value = "type/update",method = RequestMethod.POST)
    public Result updateProduct(FmeaType fmeaType){
        return fmeaTypeService.update(fmeaType);
    }



    /**
     * @Author yyk
     * @Description //TODO 查询产品信息,分页
     * @Date 2020/5/20 15:57
     * @Param [name]
     * @return com.rb.fmea.result.ResultDto
     **/
    @ApiOperation(value = "查询分页",notes = "查询分页")
    @RequestMapping(value = "type/selectPage",method = RequestMethod.GET)
    public ResultDto selectPage(String name, PageParameter pageParameter){
        return fmeaTypeService.selectPage(name,pageParameter);
    }


    /**
     * @Author yyk
     * @Description //TODO 查询所有
     * @Date 2020/6/19 10:31
     * @Param [name, pageParameter]
     * @return com.rb.fmea.result.ResultDto
     **/
    @ApiOperation(value = "查询所有",notes = "查询所有")
    @RequestMapping(value = "type/selectAll",method = RequestMethod.GET)
    public Result selectAll(){
        return fmeaTypeService.selectAll();
    }

    /**
     * @Author yyk
     * @Description //TODO 删除
     * @Date 2020/6/19 10:36
     * @Param []
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除",notes = "删除")
    @RequestMapping(value = "type/delete/{ids}",method = RequestMethod.GET)
    public Result selectAll(@PathVariable("ids") String ids){
        return fmeaTypeService.delete(ids);
    }

    /**
     * @Author yyk
     * @Description //TODO 增加
     * @Date 2020/6/19 10:41
     * @Param [fmeaType]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "增加",notes = "增加")
    @RequestMapping(value = "type/insert",method = RequestMethod.POST)
    public Result selectAll(FmeaType fmeaType){
        return fmeaTypeService.insert(fmeaType);
    }


    /**
     * @Author yyk
     * @Description //TODO 在产品下添加类型/方向
     * @Date 2020/6/19 10:46
     * @Param [productId, typeId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "在产品下添加类型/方向",notes = "在产品下添加类型/方向")
    @RequestMapping(value = "type/insertByproduct",method = RequestMethod.POST)
    public Result insetByProduct(int productId,String typeId){
        return fmeaTypeService.insetByProduct(productId,typeId);
    }

    /**
     * @Author yyk
     * @Description //TODO 在产品下查询
     * @Date 2020/6/19 10:54
     * @Param [productId, typeId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "在产品下查询",notes = "在产品下查询")
    @RequestMapping(value = "type/selectByProduct",method = RequestMethod.POST)
    public Result selectByProduct(int productId){
        return fmeaTypeService.selectByProduct(productId);
    }

    /**
     * @Author yyk
     * @Description //TODO 在产品下删除
     * @Date 2020/6/19 11:00
     * @Param [id]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "在产品下删除",notes = "在产品下删除")
    @RequestMapping(value = "type/deleteByProduct/{ids}",method = RequestMethod.GET)
    public Result deleteByProduct(@PathVariable("ids") String ids){
        return fmeaTypeService.deleteByProduct(ids);
    }

}
