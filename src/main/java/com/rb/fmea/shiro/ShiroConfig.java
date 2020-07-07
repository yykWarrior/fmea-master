package com.rb.fmea.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @version v1.0
 * @ClassName: ShiroConfig
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/28 11:29
 */
@Configuration
public class ShiroConfig {



    /**
     * @Author yyk
     * @Description //TODO 创建ShiroFilterFactoryBean
     * @Date 2020/5/28 13:19
     * @Param [defaultWebSecurityManager]
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     **/

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //过滤资源
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        /*map.put("/fmeaFunction/selectOneFmea/*","perms[fmeaFunction:selectOneFmea]");
        map.put("/fmeaFunction/selectFunctionRelate/*","perms[function:selectFunctionRelate]");*/
        //map.put("/*","authc");
        map.put("/*","anon");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置跳转到登录页面，登录请求
        shiroFilterFactoryBean.setLoginUrl("user/login");
        //成功后跳转到首页
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //验证不成功后跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("login.html");

        return shiroFilterFactoryBean;
    }


    /**
     * @Author yyk
     * @Description //TODO 创建DefaultWebSecurityManager
     * @Date 2020/5/28 13:19
     * @Param [userRealm]
     * @return org.apache.shiro.web.mgt.DefaultWebSecurityManager
     **/
    @Bean
    public DefaultWebSecurityManager getSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }

    /**
     * @Author yyk
     * @Description //TODO 创建UserRealm
     * @Date 2020/5/28 13:19
     * @Param []
     * @return com.rb.fmea.shiro.UserRealm
     **/

    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
