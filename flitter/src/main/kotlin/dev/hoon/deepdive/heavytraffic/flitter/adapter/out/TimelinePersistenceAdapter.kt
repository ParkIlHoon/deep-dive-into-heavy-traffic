package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.TimelineRepository
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.TimelinePersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TimelinePersistenceAdapter(
    private val timelineRepository: TimelineRepository
): TimelinePersistencePort {
    override fun save(timeline: Timeline): Timeline {
        TODO("Not yet implemented")
    }

    override fun saveAll(timelines: List<Timeline>) {
        TODO("Not yet implemented")
    }

    override fun findAllByMemberId(memberId: UUID, cursorRequest: CursorRequest): CursorResponse<Timeline> {
        TODO("Not yet implemented")
    }

    override fun findAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>): List<Timeline> {
        TODO("Not yet implemented")
    }

    override fun deleteAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>) {
        TODO("Not yet implemented")
    }
}