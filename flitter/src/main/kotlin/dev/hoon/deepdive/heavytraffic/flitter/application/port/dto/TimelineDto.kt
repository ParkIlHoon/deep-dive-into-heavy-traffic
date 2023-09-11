package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto

import java.time.LocalDateTime

class TimelineDto {
    data class Response(
        val id: Long,
        val memberId: Long,
        val postId: Long,
        val createdAt: LocalDateTime
    )
}
