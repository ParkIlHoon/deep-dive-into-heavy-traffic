@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.AlreadyFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotUnFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.NotFollowException
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
    override fun follow(
        followerMemberId: UUID,
        memberId: UUID,
    ) {
        followPort.find(followerMemberId, memberId)?.let { throw AlreadyFollowException() }
        try {
            followPort.create(Follow(followerMemberId = followerMemberId, memberId = memberId))
                .let { messageQueuePort.publishFollowEvent(followerMemberId, memberId) }
        } catch (e: Exception) {
            throw CannotFollowException(e)
        }
    }

    @Transactional
    override fun unFollow(
        followerMemberId: UUID,
        memberId: UUID,
    ) {
        val follow = followPort.find(followerMemberId, memberId) ?: throw NotFollowException()
        try {
            followPort.delete(follow)
                .let { messageQueuePort.publishUnFollowEvent(followerMemberId, memberId) }
        } catch (e: Exception) {
            throw CannotUnFollowException(e)
        }
    }
}
