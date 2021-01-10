package com.bluebik.shorturl.apidocs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.bluebik.shorturl.controller")
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bluebik.shorturl.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo());

    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("Shorturl Application")
                .description("Shorturl Application")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
