package dev.hoon.deepdive.heavytraffic.flitter.worker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication(scanBasePackages = ["dev.hoon.deepdive.heavytraffic.flitter"])
class FlitterWorkerApplication

fun main(args: Array<String>) {
    runApplication<FlitterWorkerApplication>(*args)
}
