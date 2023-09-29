package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.FollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.FollowPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class FollowService(
    private val followPersistencePort: FollowPersistencePort,
    private val messageQueuePort: MessageQueuePort,
) : FollowingUseCase {

    @Transactional
    override fun follow(followerId: UUID, followId: UUID) {
        followPersistencePort.save(Follow(followerMemberId = followerId, memberId = followId))
        messageQueuePort.publishFollowEvent(followerId, followId)
    }

    @Transactional
    override fun unFollow(followerId: UUID, followId: UUID) {
        followPersistencePort.delete(Follow(followerMemberId = followerId, memberId = followId))
        messageQueuePort.publishUnFollowEvent(followerId, followId)
    }
}
