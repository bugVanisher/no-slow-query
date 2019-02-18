package com.mbappe.newsql.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义资源映射
 */
@Configuration
public class MvcConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 配置静态资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");

        super.addResourceHandlers(registry);
    }

}