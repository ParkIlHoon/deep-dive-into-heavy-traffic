package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import java.util.*

interface FollowPersistencePort {
    fun save(follow: Follow): Follow

    fun delete(follow: Follow)

    fun findAllByFollowMemberId(memberId: UUID): List<Follow>

    fun findAllByFollowerMemberId(memberId: UUID): List<Follow>
}