package dev.hoon.deepdive.heavytraffic.flitter.core.event

import java.time.LocalDateTime
import java.util.UUID

data class PostLikeEvent(
    val postId: UUID,
    val memberId: UUID,
    val likedAt: LocalDateTime,
)
