package dev.hoon.deepdive.heavytraffic.flitter.core.event

import java.time.LocalDateTime
import java.util.*

data class PostWroteEvent(
    val postId: UUID,
    val writerId: UUID,
    val postedAt: LocalDateTime,
)
