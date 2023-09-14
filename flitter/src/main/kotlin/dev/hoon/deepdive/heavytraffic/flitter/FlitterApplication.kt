package dev.hoon.deepdive.heavytraffic.flitter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class FlitterApplication

fun main(args: Array<String>) {
    runApplication<FlitterApplication>(*args)
}
