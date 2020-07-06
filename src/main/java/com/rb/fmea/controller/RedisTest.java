package com.rb.fmea.controller;


import com.rb.fmea.config.RedisConfig;
import com.rb.fmea.config.RedisUtil;
import com.rb.fmea.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @version v1.0
 * @ClassName: RedisTest
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/29 14:55
 */
@ApiIgnore
@Api(description = "redis")
@RestController
public class RedisTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


   /* @Autowired
    private RedisUtil redisUtil;*/

    @RequestMapping("redis/add")
    @ApiOperation(value = "增加",notes = "增加")
    public  Result add(){
        redisTemplate.watch("k1");
        redisTemplate.multi();
        redisTemplate.opsForValue().increment("k1", 1);
        redisTemplate.exec();
        //redisUtil.incr("k1",1);
        return Result.success();
    }
}
