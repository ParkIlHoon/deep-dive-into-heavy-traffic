package dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto

import java.util.*

data class UnFollowEvent(
    val followerId: UUID,
    val followId: UUID
)
