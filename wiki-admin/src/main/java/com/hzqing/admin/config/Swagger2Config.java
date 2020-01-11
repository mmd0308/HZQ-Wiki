package com.hzqing.admin.config;

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
 * Swagger配置文件
 * @author hzqing
 * @date 2020-01-11 16:20
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hzqing.admin.controller"))  //指定暴露的AIP接口
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("HZQ-WIKI API 文档") // 标题
                .description("这是HZQ-WIKI 服务器端基于Restful风格的API文档") // 说明
                .termsOfServiceUrl("http://www.hzqing.com/")   //指定访问的URL
                .contact(new Contact("hzqing", "", "hengzhaoqing@163.com"))
                .build();
    }}
