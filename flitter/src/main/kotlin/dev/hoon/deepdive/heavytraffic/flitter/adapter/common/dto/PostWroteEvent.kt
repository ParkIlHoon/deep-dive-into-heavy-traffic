package dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto

data class PostWroteEvent(
    val postId: Long,
    val writerId: Long
)
