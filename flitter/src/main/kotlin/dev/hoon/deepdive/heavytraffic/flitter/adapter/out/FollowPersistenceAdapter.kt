package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper.FollowMapper
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.FollowRepository
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.FollowPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class FollowPersistenceAdapter(
    private val followRepository: FollowRepository
): FollowPersistencePort {
    @Transactional
    override fun save(follow: Follow): Follow =
        FollowMapper.toEntity(follow)
            .let {
                followRepository.save(it)
            }.let {
                FollowMapper.toDomain(it)
            }

    @Transactional
    override fun delete(follow: Follow) =
        FollowMapper.toEntity(follow)
            .let {
                followRepository.delete(it)
            }

    override fun findAllByFollowMemberId(memberId: UUID): List<Follow> =
        followRepository.findAllByMemberId(memberId)
            .map {
                FollowMapper.toDomain(it)
            }

    override fun findAllByFollowerMemberId(memberId: UUID): List<Follow> =
        followRepository.findAllByFollowerMemberId(memberId)
            .map {
                FollowMapper.toDomain(it)
            }
}