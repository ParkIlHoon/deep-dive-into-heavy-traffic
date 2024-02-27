package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.TimelineRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.TimelinePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TimelineAdapter(
    private val timelineRepository: TimelineRepository,
) : TimelinePort {
    override fun get(timelineId: UUID, memberId: UUID, size: Long): List<Timeline> =
        timelineRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(timelineId, memberId, size)

    override fun get(memberId: UUID, size: Long): List<Timeline> =
        timelineRepository.findAllByMemberIdAndOrderByIdDesc(memberId, size)

    @Transactional
    override fun create(timelines: List<Timeline>) {
        timelineRepository.saveAll(timelines)
    }
}
