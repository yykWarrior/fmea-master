package com.rb.fmea.shiro;

import com.rb.fmea.entities.Perm;
import com.rb.fmea.entities.User;
import com.rb.fmea.service.PermService;
import com.rb.fmea.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: UserShiro
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/28 11:30
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private PermService permService;
    /**
     * @Author yyk
     * @Description //TODO  做资源管理
     * @Date 2020/5/28 13:09
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String userName =(String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询用户信息
        User user = userService.selectByName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //根据用户查询用户的角色和权限
        List<Perm> permList=permService.selectPermByUserId(user.getId());
        for(Perm perm:permList){
            simpleAuthorizationInfo.addStringPermission(perm.getCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * @Author yyk
     * @Description //TODO 做认证管理
     * @Date 2020/5/28 13:10
     * @Param [authenticationToken]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(authenticationToken==null){
            return null;
        }
        String userName = authenticationToken.getPrincipal().toString();
        //根据用户名查询用户信息
        User user=userService.selectByName(userName);
        if(user==null){
            return null;
        }
        //验证密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), "");
        return simpleAuthenticationInfo;
    }
}
