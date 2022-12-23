package com.example.springdocswagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI movieApi() {
        return new OpenAPI()
                .info(new Info().title("movie API")
                        .description("영화 관련 API")
                        .version("1.0.0")
                        .license(new License().name("라이센스 이름"))
                );
    }

    //API들을 그룹으로 묶을 수 있음. 그룹이 필요없으면 위에만 있어도 됨
    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
                .group("v1 api 그룹")    //해당 그룹 이름
                .pathsToMatch("/v1/**")  //URI 규칙
                .build();
    }

    @Bean
    public GroupedOpenApi v2Api() {
        return GroupedOpenApi.builder()
                .group("v2 api 그룹")
                .pathsToMatch("/v2/**")
//                .addOpenApiMethodFilter(method -> method.isAnnotationPresent())  해당 어노테이션을 가지고 있는지
                .build();
    }
}
