package com.hzqing.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hzqing
 * @date 2019-06-10 08:56
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${hzq.fs.path}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fs/doc/**","/fs/user/**")
                    .addResourceLocations("file://"+ filePath + "doc/","file://"+ filePath + "user/");
    }


}