package com.ArtSeeReal.pro.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                        .info(apiInfo());
        }
        private Info apiInfo() {
                return new Info()
                        .title("ArtSeeReal API Documentaion")
                        .description("아트씨리얼의 Swagger UI APIs")
                        .version("v1.0.0");
        }

        @Bean
        public OperationCustomizer operationCustomizer() {
                return (operation, handlerMethod) -> {
                        operation.addParametersItem(
                                new Parameter()
                                        .in(ParameterIn.HEADER.toString())
                                        .required(true)
                                        .description("헤더")
                                        .name("Authorizations")
                                        .example("token")
                        );

                        return operation;
                };
        }
}
