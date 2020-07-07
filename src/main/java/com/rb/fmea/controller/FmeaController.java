package com.rb.fmea.controller;


import com.rb.fmea.entities.Fmea;
import com.rb.fmea.result.Result;
import com.rb.fmea.service.FmeaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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

    /**
     * @Author yyk
     * @Description //TODO 查询一个产品下对应的基础,家族,新品fmea
     * @Date 2020/6/19 11:17
     * @Param [productId]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value = "查询一个产品下对应的基础,家族,新品fmea",notes = "查询一个产品下对应的基础,家族,新品fmea")
    @RequestMapping(value = "fmea/selectByProductId/{productId}",method = RequestMethod.GET)
    public Result selectByProductId(@PathVariable("productId") int productId){
        return fmeaService.selectByProductId(productId);
    }

    /**
     * @Author yyk
     * @Description //TODO 点击fmea完成，更新状态和完成时间
     * @Date 2020/7/1 16:19
     * @Param
     * @return
     **/
    @ApiOperation(value = "点击fmea完成，更新状态和完成时间",notes = "点击fmea完成，更新状态和完成时间")
    @RequestMapping(value = "/fmea/updateState/{fmeaId}",method = RequestMethod.GET)
    public Result updateState(@PathVariable("fmeaId") int fmeaId){
        return fmeaService.updateState(fmeaId);
    }


    /**
     * @Author yyk
     * @Description //TODO fmea间复制，同级复制和上下级复制，0表示同级复制，1表示上下级复制
     * @Date 2020/7/7 8:43
     * @Param [fmeaId, type]
     * @return com.rb.fmea.result.Result
     **/
    @ApiOperation(value ="fmea间复制(type为0是同级复制,type为1是上下级复制)",notes = "fmea间复制(type为0是同级复制,type为1是上下级复制)")
    @RequestMapping(value = "/fmea/copy/{fmeaId}",method = RequestMethod.GET)
    public Result fmeaCopy(@Param("fmeaId") int fmeaId,  int type){
        return fmeaService.fmeaCopy(fmeaId,type);
    }
}
