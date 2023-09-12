package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow

interface FollowPersistencePort {
    fun save(follow: Follow): Follow

    fun delete(follow: Follow)

    fun findAllByFollowMemberId(memberId: Long): List<Follow>

    fun findAllByFollowerMemberId(memberId: Long): List<Follow>
}