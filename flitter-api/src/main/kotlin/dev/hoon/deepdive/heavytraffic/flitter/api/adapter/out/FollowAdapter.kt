@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.FollowRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.spec.FollowSpecs
import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.spec.SpecBuilder
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
    override fun delete(
        followerMemberId: UUID,
        memberId: UUID,
    ) = followRepository.delete(
        SpecBuilder.builder(Follow::class.java)
            .and(FollowSpecs.followerMemberId(followerMemberId))
            .and(FollowSpecs.memberId(memberId))
            .toSpec(),
    )

    @Transactional
    override fun delete(follow: Follow) {
        followRepository.delete(follow)
    }

    override fun find(
        followerMemberId: UUID,
        memberId: UUID,
    ): Follow? =
        followRepository.findAll(
            SpecBuilder.builder(Follow::class.java)
                .and(FollowSpecs.followerMemberId(followerMemberId))
                .and(FollowSpecs.memberId(memberId))
                .toSpec(),
        ).firstOrNull()
}
