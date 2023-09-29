package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper.FollowMapper
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.FollowRepository
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.FollowPort
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
    override fun create(follow: Follow): Follow =
        FollowMapper.toEntity(follow)
            .let { followRepository.save(it) }
            .let { FollowMapper.toDomain(it) }

    @Transactional
    override fun delete(followerId: UUID, followId: UUID) = followRepository.deleteByFollowerMemberIdAndMemberId(followerId, followId)

    @Transactional
    override fun delete(followId: UUID) = followRepository.deleteAllByMemberId(followId)

    override fun getByFollowMemberId(memberId: UUID): List<Follow> =
        followRepository.findAllByMemberId(memberId)
            .map { FollowMapper.toDomain(it) }

    override fun getByFollowerMemberId(memberId: UUID): List<Follow> =
        followRepository.findAllByFollowerMemberId(memberId)
            .map { FollowMapper.toDomain(it) }
}
