package com.basic.zjgfbcc.common.config;

import com.basic.zjgfbcc.controller.BaseController;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * 配置拦截器
* <p>Title: InterceptorConfig</p>  
* <p>Description: </p>  
* @author hero
 */
@Configuration
public class InterceptorConfig extends BaseController implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
    	registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**") //所有路径都被拦截
                .excludePathPatterns("/app/**","/file/**","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");//添加不拦截路径
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    	
	  //文件磁盘url 映射
	  registry.addResourceHandler("/file/**").addResourceLocations("file:/"+filePath);
    }
    
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory(); //单个文件最大
        factory.setMaxFileSize("500MB"); //KB,MB /// 设置总上传数据总大小
        factory.setMaxRequestSize("502400KB");
        return factory.createMultipartConfig();
    }
}
