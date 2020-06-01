package com.rb.fmea.controller;


import com.rb.fmea.entities.Fmea;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * @version v1.0
 * @ClassName: FmeaController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/21 10:15
 */
@Api(description = "所有fmea信息接口")
@RestController
public class FmeaController {
    @Autowired
    private FmeaService fmeaService;

    /**
     * @Author yyk
     * @Description //TODO 添加fmea信息
     * @Date 2020/5/21 10:18
     * @Param [fmea]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "添加fmea",notes = "添加fmea")
    @RequestMapping(value = "fmea/insert",method = RequestMethod.POST)
    public Result insert(Fmea fmea){
        return fmeaService.insert(fmea);
    }

    /**
     * @Author yyk
     * @Description //TODO 查询所有基础fmea
     * @Date 2020/5/21 10:26
     * @Param []
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询所有基础fmea",notes = "查询所有基础fmea")
    @RequestMapping(value = "fmea/selectBasicFmea",method = RequestMethod.GET)
    public Result select(){
        return fmeaService.selectBasicFmea();
    }


    /**
     * @Author yyk
     * @Description //TODO 修改fmea信息
     * @Date 2020/5/21 10:54
     * @Param [fmea]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "修改fmea信息",notes = "修改fmea信息")
    @RequestMapping(value = "fmea/update",method = RequestMethod.POST)
    public Result update(Fmea fmea){
        return fmeaService.update(fmea);
    }

    /**
     * @Author yyk
     * @Description //TODO 删除fmea
     * @Date 2020/5/21 11:01
     * @Param [ids]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "删除fmea",notes = "删除fmea")
    @RequestMapping(value = "fmea/delete/{ids}",method = RequestMethod.GET)
    public Result delete(@PathVariable("ids") String ids){
        return fmeaService.delete(ids);
    }

    @ApiOperation(value = "查询一个产品下对应的基础,家族,新品fmea",notes = "查询一个产品下对应的基础,家族,新品fmea")
    @RequestMapping(value = "fmea/selectByProductId/{productId}",method = RequestMethod.GET)
    public Result selectByProductId(@PathVariable("productId") int productId){
        return fmeaService.selectByProductId(productId);
    }
}
