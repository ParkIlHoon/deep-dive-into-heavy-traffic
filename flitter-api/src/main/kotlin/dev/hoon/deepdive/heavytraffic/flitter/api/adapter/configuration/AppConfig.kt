@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.hoon.deepdive.heavytraffic.flitter.core.constants.DateConstants.Companion.DATE_TIME_FORMATTER
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class AppConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        val javaTimeModule = JavaTimeModule()

        javaTimeModule.addSerializer(LocalDateTime::class.java, CustomLocalDateTimeSerializer())
        javaTimeModule.addDeserializer(LocalDateTime::class.java, CustomLocalDateTimeDeSerializer())

        return objectMapper.registerKotlinModule().registerModule(JavaTimeModule())
    }

    class CustomLocalDateTimeSerializer() : JsonSerializer<LocalDateTime>() {
        override fun serialize(
            value: LocalDateTime,
            gen: JsonGenerator,
            serializers: SerializerProvider,
        ) {
            gen.writeString(DATE_TIME_FORMATTER.format(value))
        }
    }

    class CustomLocalDateTimeDeSerializer() : JsonDeserializer<LocalDateTime>() {
        override fun deserialize(
            p: JsonParser,
            ctxt: DeserializationContext,
        ): LocalDateTime {
            return LocalDateTime.parse(p.text, DATE_TIME_FORMATTER)
        }
    }
}
