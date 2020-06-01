package com.rb.fmea.controller;

import com.rb.fmea.dao.UserMapper;
import com.rb.fmea.entities.User;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.UserService;
import com.rb.fmea.util.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.UUID;

/**
 * @version v1.0
 * @ClassName: UserController
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/16 15:48
 */
@Api(description = "用户操作接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    //@ApiImplicitParam(paramType = "query", required = true, dataType = "Integer")
    @ApiOperation(value = "添加用户",notes = "添加用户")
    public Result add(String name,String password,int role){
            return userService.add(name,password,role);
    }


    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户",notes = "删除用户")
    public String delete(){
        try {
            System.out.println(UUID.randomUUID());
            return "成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户",notes = "修改用户")
    public String update(){
        try {
            System.out.println(UUID.randomUUID());
            return "成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "select",method = RequestMethod.GET)
    @ApiOperation(value = "查找用户",notes = "查找用户")
    public String select(){
        try {
            System.out.println(UUID.randomUUID());
            System.out.println("aaa");
            return "成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }



    /**
     * @Author yyk
     * @Description //TODO 用户登录操作
     * @Date 2020/5/28 13:32
     * @Param [user]
     * @return com.rb.fmea.result.Result
     **/
    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public Result login(User user){
        //密码加密
        String password = Md5Util.twiceMd5(user.getPassword());
        user.setPassword(password);
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(),user.getPassword());
        //做登录操作
        try {
            subject.login(usernamePasswordToken);
            return Result.success();
        } catch (UnknownAccountException e) {
            return Result.error(new CodeMsg(ReturnCode.USER_NOT_EXIST,"用户名不存在"));
        }catch (IncorrectCredentialsException e){
            return Result.error(new CodeMsg(ReturnCode.USER_ACCOUNT_ERROR,"用户密码错误"));
        }
    }

}
