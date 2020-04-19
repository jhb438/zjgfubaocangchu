package com.basic.zjgfbcc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.basic.zjgfbcc.common.customclass.PassToken;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    //swagger2的基本配置
    @Bean
    public Docket createRestApi() {
    	ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();  
    	ticketPar.name("X-Auth-Token").description("user ticket")
    	.modelRef(new ModelRef("string")).parameterType("header") 
    	.required(false).build(); //header中的ticket参数非必填，传空也可以
    	pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
    	
    	 return new Docket(DocumentationType.SWAGGER_2)
         		.select()
         		.apis(RequestHandlerSelectors.any())
         		.paths(PathSelectors.any())
         		.build()
                 .globalOperationParameters(pars)  
                 .apiInfo(apiInfo());
    	
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//                //接口包扫描
//                .apis(RequestHandlerSelectors.basePackage("com.basic.zjgfbcc"))
//                .paths(PathSelectors.any()).build();
    }
 
    //构建api文档信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("使用swagger2构建后台的接口文档")
                //创建人相关信息
                .contact(new Contact("hero", "www.inyourheart.con", "xxx@qq.com"))
                //描述
                .description("接口文档，这是描述")
                //版本 
                .version("1.0")
                .build();
    }
 
}

