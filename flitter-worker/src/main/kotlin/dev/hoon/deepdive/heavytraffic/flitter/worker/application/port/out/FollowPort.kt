package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import java.util.*

interface FollowPort {
    fun delete(followerId: UUID, followId: UUID)

    fun delete(followId: UUID)

    fun getByFollowMemberId(memberId: UUID): List<Follow>
}
