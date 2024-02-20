package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan("dev.hoon.deepdive.heavytraffic.flitter.domain")
class JpaConfig {
}