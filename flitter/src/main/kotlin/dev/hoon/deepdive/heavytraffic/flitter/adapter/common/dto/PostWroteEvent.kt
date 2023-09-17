package dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto

import java.util.*

data class PostWroteEvent(
    val postId: UUID,
    val writerId: UUID
)
