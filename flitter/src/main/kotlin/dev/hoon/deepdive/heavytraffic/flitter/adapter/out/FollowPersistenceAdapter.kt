package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

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
    override fun save(follow: Follow): Follow {
        TODO("Not yet implemented")
    }

    override fun delete(follow: Follow) {
        TODO("Not yet implemented")
    }

    override fun findAllByFollowMemberId(memberId: UUID): List<Follow> {
        TODO("Not yet implemented")
    }

    override fun findAllByFollowerMemberId(memberId: UUID): List<Follow> {
        TODO("Not yet implemented")
    }
}