package com.rb.fmea.config;

/**
 * @version v1.0
 * @ClassName: Swagger2
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/5/16 10:09
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



/**
 * @Author yyk
 * @Description //TODO swagger2配置类
 * @Date 2020/5/16 17:33
 * @Param
 * @return
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.rb.fmea.controller"))
                .paths(PathSelectors.any())
                .build();
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("fmea系统API")
                //创建人
                .contact(new Contact("yyk", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}