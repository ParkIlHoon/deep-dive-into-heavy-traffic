package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.common.dto

import java.util.*

data class FollowEvent(
    val followerId: UUID,
    val followId: UUID
)
