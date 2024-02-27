package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

    class CustomLocalDateTimeSerializer(): JsonSerializer<LocalDateTime>() {
        override fun serialize(value: LocalDateTime, gen: JsonGenerator, serializers: SerializerProvider) {
            gen.writeString(formatter.format(value))
        }
    }

    class CustomLocalDateTimeDeSerializer(): JsonDeserializer<LocalDateTime>(){
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDateTime {
            return LocalDateTime.parse(p.text, formatter)
        }
    }

    companion object {
        private const val dateTimeFormat = "yyyy-MM-dd HH:mm:ss"
        private val formatter = DateTimeFormatter.ofPattern(dateTimeFormat, Locale.KOREA)
    }
}