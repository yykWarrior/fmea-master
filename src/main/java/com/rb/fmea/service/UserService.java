package com.rb.fmea.service;

import com.rb.fmea.entities.User;
import com.rb.fmea.result.Result;

/**
 * @version v1.0
 * @ClassName: UserService
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/28 13:56
 */
public interface UserService {
    /**
     * @Author yyk
     * @Description //TODO 根据名称查询用户信息
     * @Date 2020/5/28 13:58
     * @Param [userName]
     * @return com.rb.fmea.entities.User
     **/
    User selectByName(String userName);

    /**
     * @Author yyk
     * @Description //TODO 注册用户
     * @Date 2020/5/28 14:21
     * @Param [name, password, role]
     * @return com.rb.fmea.result.Result
     **/
    Result add(String name, String password, int role);
}
