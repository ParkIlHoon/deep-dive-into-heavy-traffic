package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import java.util.*

interface FollowPort {
    fun create(follow: Follow): Follow

    fun delete(followerId: UUID, followId: UUID)
}
