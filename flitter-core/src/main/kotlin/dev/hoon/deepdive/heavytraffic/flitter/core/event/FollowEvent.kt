package dev.hoon.deepdive.heavytraffic.flitter.core.event

import java.util.*

data class FollowEvent(
    val followerId: UUID,
    val followId: UUID
)
