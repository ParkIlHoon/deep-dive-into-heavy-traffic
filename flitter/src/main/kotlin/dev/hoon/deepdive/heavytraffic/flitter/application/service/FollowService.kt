package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.FollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.FollowPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FollowService(
    private val followPersistencePort: FollowPersistencePort
): FollowingUseCase {

    @Transactional
    override fun follow(followerId: Long, followId: Long) {
        followPersistencePort.save(Follow(followerMemberId = followerId, followMemberId = followId))
        //TODO 타임라인 생성
    }

    @Transactional
    override fun unFollow(followerId: Long, followId: Long) {
        followPersistencePort.delete(Follow(followerMemberId = followerId, followMemberId = followId))
        //TODO 타임라인 제거
    }
}