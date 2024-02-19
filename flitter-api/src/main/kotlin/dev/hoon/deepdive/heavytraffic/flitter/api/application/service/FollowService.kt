package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotUnFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.FollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.UnFollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.follow.Follow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class FollowService(
    private val followPort: FollowPort,
    private val memberPort: MemberPort,
    private val messageQueuePort: MessageQueuePort,
) : FollowingUseCase, UnFollowingUseCase {

    @Transactional
    override fun follow(followerId: UUID, followId: UUID) {
        validateMember(followerId) { CannotFollowException(it) }
        validateMember(followId) { CannotFollowException(it) }

        val follow = followPort.create(Follow(followerMemberId = followerId, memberId = followId))
        follow.id?.let { messageQueuePort.publishFollowEvent(followerId, followId) }
    }

    @Transactional
    override fun unFollow(followerId: UUID, followId: UUID) {
        validateMember(followerId) { CannotUnFollowException(it) }

        followPort.delete(followerId = followerId, followId = followId)
        messageQueuePort.publishUnFollowEvent(followerId, followId)
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}
