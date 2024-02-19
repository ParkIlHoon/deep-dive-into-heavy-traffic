package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.FollowRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class FollowAdapter(
    private val followRepository: FollowRepository,
) : FollowPort {
    @Transactional
    override fun create(follow: Follow): Follow = followRepository.save(follow)

    @Transactional
    override fun delete(followerId: UUID, followId: UUID) = followRepository.deleteByFollowerMemberIdAndMemberId(followerId, followId)
}
