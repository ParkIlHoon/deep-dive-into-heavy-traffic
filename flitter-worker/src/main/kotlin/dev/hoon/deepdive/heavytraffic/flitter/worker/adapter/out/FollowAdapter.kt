@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.FollowRepository
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.FollowPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class FollowAdapter(
    private val followRepository: FollowRepository,
) : FollowPort {
    @Transactional
    override fun delete(
        followerId: UUID,
        followId: UUID,
    ) = followRepository.deleteByFollowerMemberIdAndMemberId(followerId, followId)

    @Transactional
    override fun delete(followId: UUID) = followRepository.deleteAllByMemberId(followId)

    override fun getByFollowMemberId(memberId: UUID): List<Follow> = followRepository.findAllByMemberId(memberId)
}
