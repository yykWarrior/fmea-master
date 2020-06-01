package com.rb.fmea.service.impl;

import com.rb.fmea.dao.UserMapper;
import com.rb.fmea.dao.UserRoleMapper;
import com.rb.fmea.entities.User;
import com.rb.fmea.entities.UserRole;
import com.rb.fmea.result.CodeMsg;
import com.rb.fmea.result.Result;
import com.rb.fmea.result.ReturnCode;
import com.rb.fmea.service.UserService;
import com.rb.fmea.util.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @version v1.0
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/28 13:56
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    /**
     * @Author yyk
     * @Description //TODO 根据名称查询用户信息
     * @Date 2020/5/28 13:58
     * @Param [userName]
     * @return com.rb.fmea.entities.User
     **/
    @Override
    public User selectByName(String userName) {
        return userMapper.selectByName(userName);
    }

    /**
     * @Author yyk
     * @Description //TODO 用户注册
     * @Date 2020/5/28 14:16
     * @Param [name, password, role]
     * @return com.rb.fmea.result.Result
     **/
    @Override
    public Result add(String name, String password, int role) {
        try {
            password = Md5Util.twiceMd5(password);
            //插入到用户表
            User user = new User(name, password, "");
            userMapper.insert(user);
            //插入到角色用户表
            userRoleMapper.insert(new UserRole(user.getId(),role));
            return Result.success();
        } catch (Exception e) {
            return Result.error(new CodeMsg(ReturnCode.DATA_IS_WRONG,"注册用户失败，原因："+e.getMessage()));
        }
    }
}
