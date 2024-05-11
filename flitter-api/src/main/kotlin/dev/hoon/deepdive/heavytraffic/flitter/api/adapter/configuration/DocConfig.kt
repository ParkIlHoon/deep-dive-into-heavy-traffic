package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * API Document 설정
 */
@Configuration
class DocConfig {
    @Bean
    fun openAPI(): OpenAPI =
        OpenAPI().info(
            Info()
                .title("Filtter API")
                .version("1.0.0")
                .contact(
                    Contact().name("박일훈").email("chiwoo2074@gmail.com"),
                ),
        )

    @Bean
    @SuppressWarnings("kotlin:S100", "ktlint:standard:function-naming")
    fun apiV1_0(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("v1.0")
            .displayName("v1.0 :: API 버전 1.0")
            .pathsToMatch("/api/v1.0/**")
            .build()
}
