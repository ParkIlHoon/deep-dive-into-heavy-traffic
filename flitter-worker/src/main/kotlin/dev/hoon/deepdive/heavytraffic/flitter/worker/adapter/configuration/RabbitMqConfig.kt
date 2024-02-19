package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.properties.RabbitMqProperties
import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.interceptor.RetryInterceptorBuilder
import org.springframework.retry.interceptor.RetryOperationsInterceptor

/**
 * RabbitMQ 설정
 */
@EnableRabbit
@Configuration
class RabbitMqConfig(
    private val rabbitMqProperties: RabbitMqProperties,
) {
    @Bean
    fun objectMapper() = ObjectMapper().registerKotlinModule()

    @Bean
    fun messageConverter(objectMapper: ObjectMapper) = Jackson2JsonMessageConverter(objectMapper)

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, messageConverter: MessageConverter) =
        RabbitTemplate(connectionFactory)
            .apply {
                isChannelTransacted = true
                setMessageConverter(messageConverter)
            }

    @Bean
    fun retryOperationsInterceptor(rabbitTemplate: RabbitTemplate): RetryOperationsInterceptor =
        RetryInterceptorBuilder
            .stateless()
            .maxAttempts(rabbitMqProperties.retryCount)
            .build()

    @Bean
    fun messageListenerContainerFactory(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter,
        retryOperationsInterceptor: RetryOperationsInterceptor,
    ): SimpleRabbitListenerContainerFactory =
        SimpleRabbitListenerContainerFactory()
            .apply {
                setConnectionFactory(connectionFactory)
                setConcurrentConsumers(rabbitMqProperties.concurrentConsumers)
                setMaxConcurrentConsumers(rabbitMqProperties.maxConcurrentConsumers)
                setMessageConverter(messageConverter)
                setAdviceChain(retryOperationsInterceptor)
                setAcknowledgeMode(AcknowledgeMode.MANUAL)
                setDefaultRequeueRejected(false)
            }
}
