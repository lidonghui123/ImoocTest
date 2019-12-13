package com.springBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//加载springboot有个专门标签
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //swagger固定的写法
    @Bean
    public Docket api(){
        //返回Docket2，固定写法。
        return new Docket(DocumentationType.SWAGGER_2)
                //添加apiInfo的方法，自己进行实现的
                .apiInfo(apiInfo())
                // 配置整个访问路径,默认根目
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        //生成文档
        return new ApiInfoBuilder().title("我的接口")
                //1、联系人 2、描述 3、版本
                .contact(new Contact("dazhou","","132334804@qq.com"))
                .description("这是我的swagger生成的接口文档")
                .version("2.0.0")
                .build();
    }
}
