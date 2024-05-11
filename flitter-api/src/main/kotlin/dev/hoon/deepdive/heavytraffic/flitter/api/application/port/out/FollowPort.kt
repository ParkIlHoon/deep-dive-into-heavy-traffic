@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import java.util.*

interface FollowPort {
    fun create(follow: Follow): Follow

    fun delete(
        followerMemberId: UUID,
        memberId: UUID,
    ): Long

    fun delete(follow: Follow)

    fun find(
        followerMemberId: UUID,
        memberId: UUID,
    ): Follow?
}
