package org.claytantor.configuration;

import com.google.common.base.Predicate;
import javassist.bytecode.ByteArray;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.io.*;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by claytongraham on 5/28/18.
 */
@Configuration
@EnableRedisHttpSession
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.claytantor.controller"))
                .paths(allPaths())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<String> allPaths() {
        return or(regex("/healthcheck.*"), regex("/api/intpicker.*"));
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Intpicker REST API",
                getInfoDescription(),
                "v1.0",
                "https://opensource.org/ToS",
                new Contact("Clay Graham", "claytantor.com", "claytantor@gmail.com.com"),
                "Apache License, Version 2.0", "https://opensource.org/licenses/Apache-2.0", Collections.emptyList());
    }

    private String getInfoDescription(){
        String apiInfoDescr = "A restful <strong>API</strong> that fin integers that add up to a sum.";
        try {
            apiInfoDescr = read(SwaggerConfig.class.getResourceAsStream("/apiInfo.html"));
        } catch (IOException e) {
            //apiInfoDescr = "A restful <strong>API</strong> that fin integers that add up to a sum.";
        }
        return apiInfoDescr;
    }

    public static String read(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }


}