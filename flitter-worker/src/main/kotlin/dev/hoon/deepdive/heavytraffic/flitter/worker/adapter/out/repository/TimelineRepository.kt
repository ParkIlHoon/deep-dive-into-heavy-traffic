package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TimelineRepository : JpaRepository<Timeline, UUIDPrimaryKey>, TimelineCustomRepository {
    fun findAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>): List<Timeline>

    fun deleteAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>)
    fun findAllByMemberId(memberId: UUID): List<Timeline>
    fun deleteAllByPostIdIn(postIds: List<UUID>)

    fun deleteAllByPostId(postId: UUID)

    fun deleteAllByMemberId(memberId: UUID)
}
