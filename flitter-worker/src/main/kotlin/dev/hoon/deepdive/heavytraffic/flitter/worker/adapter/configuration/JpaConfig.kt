package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.configuration

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan("dev.hoon.deepdive.heavytraffic.flitter.domain")
class JpaConfig {
    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory = JPAQueryFactory(entityManager)
}
