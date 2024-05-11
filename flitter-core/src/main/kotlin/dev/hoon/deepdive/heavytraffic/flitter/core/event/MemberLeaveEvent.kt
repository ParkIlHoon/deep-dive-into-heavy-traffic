@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.core.event

import java.util.*

data class MemberLeaveEvent(
    val memberId: UUID,
)
