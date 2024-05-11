@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto

import java.time.LocalDateTime
import java.util.*

class PostDto {
    data class Response(
        val id: UUID,
        val writerId: UUID,
        val contents: String,
        val like: Long,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    )
}
