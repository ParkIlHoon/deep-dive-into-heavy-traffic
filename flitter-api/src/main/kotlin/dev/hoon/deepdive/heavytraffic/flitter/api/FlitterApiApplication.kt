package dev.hoon.deepdive.heavytraffic.flitter.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = ["dev.hoon.deepdive.heavytraffic.flitter"])
class FlitterApiApplication

fun main(args: Array<String>) {
    runApplication<FlitterApiApplication>(*args)
}
