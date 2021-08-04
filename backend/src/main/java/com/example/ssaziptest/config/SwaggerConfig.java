package com.example.ssaziptest.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

//    private String version = "v1";
//    private String pathUri = "/api/**";
//        private String title = "SW_TEST API";
//        private String description = "SW_TEST API Docs";
//        private String basePackage = "com.example.jwt_test2.api";
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(basePackage))
//                .paths(PathSelectors.ant("/api/**"))
//                .build()
//                .apiInfo(apiInfo()) .enable(true);
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title(title)
//                .description(description)
//                .version(version)
//                .build();
//    }

    @Bean
    public Docket postsApi(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }
    private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("SSAZIP API")
                .description("STUDY ANYWHERE WITH EVERYONE").build();
    }
}
