package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.configuration

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

/**
 * Spring Cloud Open Feign 설정
 */
@Configuration
@EnableFeignClients(basePackages = ["dev.hoon.deepdive.heavytraffic.flitter"])
class FeignConfig {
}