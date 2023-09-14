package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.FollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.FollowPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FollowService(
    private val followPersistencePort: FollowPersistencePort,
    private val messageQueuePort: MessageQueuePort
): FollowingUseCase {

    @Transactional
    override fun follow(followerId: Long, followId: Long) {
        followPersistencePort.save(Follow(followerMemberId = followerId, followMemberId = followId))
        messageQueuePort.publishFollowEvent(followerId, followId)
    }

    @Transactional
    override fun unFollow(followerId: Long, followId: Long) {
        followPersistencePort.delete(Follow(followerMemberId = followerId, followMemberId = followId))
        messageQueuePort.publishUnFollowEvent(followerId, followId)
    }
}