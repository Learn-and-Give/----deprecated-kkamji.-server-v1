package com.kkamjidot.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("깜지. API 서버 V1")
                .description("학습자료 공유 기반 챌린지 서비스 플랫폼 깜지.의 API 서버 V1")
                .build();
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .groupName("kkamji.")
                .apiInfo(this.swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kkamjidot.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }



    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/swagger"), req ->
                ServerResponse.temporaryRedirect(URI.create("swagger-ui/index.html")).build());
    }
}
