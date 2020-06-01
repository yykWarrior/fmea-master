package com.rb.fmea.aspect;



import com.rb.fmea.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author yyk
 * @Description //TODO 
 * @Date 2020/5/16 16:54
 * @Param AOP日志文件
 * @return 
 **/
@Slf4j
@Aspect
@Component
public class HttpAspect {

    //@Pointcut(value = "execution(public *  com.rb.fmea.service.*.*(..) & public * com.rb.fmea.controller.*.*(..))")
    @Pointcut(value = "execution(public *  com.rb.fmea.service.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //访问时间
        log.info("date={}", DateUtil.parseTime(new Date()));
        // url
        log.info("url={}", request.getRequestURL());
        // port
        log.info("port={}", request.getRemotePort());
        // method
        log.info("method={}", request.getMethod());
        // ip
        log.info("ip={}", request.getRemoteAddr());
        // class_method
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 参数
        log.info("args={}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("response={}", object);
    }

    @AfterThrowing(throwing="ex", pointcut="log()")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable ex){
        log.info("请求出现异常:"+ex);
    }
}