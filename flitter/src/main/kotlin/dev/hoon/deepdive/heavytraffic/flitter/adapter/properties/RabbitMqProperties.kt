package dev.hoon.deepdive.heavytraffic.flitter.adapter.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.rabbit-mq")
data class RabbitMqProperties(
    val concurrentConsumers: Int,
    val maxConcurrentConsumers: Int,
    val threadNamePrefix: String,
    val retryCount: Int,
    val defaultRequeueRejected: Boolean,
)
