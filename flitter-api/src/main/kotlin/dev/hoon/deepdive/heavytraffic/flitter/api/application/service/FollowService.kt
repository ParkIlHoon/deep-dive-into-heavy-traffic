package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.FollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.UnFollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class FollowService(
    private val followPort: FollowPort,
    private val messageQueuePort: MessageQueuePort,
) : FollowingUseCase, UnFollowingUseCase {

    @Transactional
    override fun follow(followerMemberId: UUID, memberId: UUID) {
        try {
            followPort.create(Follow(followerMemberId = followerMemberId, memberId = memberId))
                .let { messageQueuePort.publishFollowEvent(followerMemberId, memberId) }
        } catch (e: Exception) {
            throw CannotFollowException(e)
        }
    }

    @Transactional
    override fun unFollow(followerId: UUID, followId: UUID) {
        followPort.delete(followerId = followerId, followId = followId)
        messageQueuePort.publishUnFollowEvent(followerId, followId)
    }
}
