package com.rb.fmea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @version v1.0
 * @ClassName: FmeaMain
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/16 8:58
 */
@EnableScheduling
@SpringBootApplication
public class FmeaMain {
    public static void main(String[] args) {
        SpringApplication.run(FmeaMain.class,args);
    }
}
