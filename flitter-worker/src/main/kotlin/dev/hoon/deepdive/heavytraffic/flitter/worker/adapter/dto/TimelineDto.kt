package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto

import java.time.LocalDateTime
import java.util.UUID

class TimelineDto {
    data class CreateRequest(
        val memberId: UUID,
        val postId: UUID,
        val postedAt: LocalDateTime
    )
}
