package dev.hoon.deepdive.heavytraffic.flitter.worker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlitterWorkerApplication

fun main(args: Array<String>) {
    runApplication<FlitterWorkerApplication>(*args)
}
