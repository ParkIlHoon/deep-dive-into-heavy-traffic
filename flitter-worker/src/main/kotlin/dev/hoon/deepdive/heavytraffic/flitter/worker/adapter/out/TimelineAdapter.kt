package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.TimelineRepository
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.TimelinePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TimelineAdapter(
    private val timelineRepository: TimelineRepository,
) : TimelinePort {
    @Transactional
    override fun saveAll(timelines: List<Timeline>): List<Timeline> = timelineRepository.saveAll(timelines)

    override fun deleteAllByPostId(postId: UUID) =
        timelineRepository.deleteAllByPostId(postId)

    override fun deleteAllByMemberId(memberId: UUID) =
        timelineRepository.deleteAllByMemberId(memberId)

    @Transactional
    override fun deleteAllByPostIdIn(postIds: List<UUID>) = timelineRepository.deleteAllByPostIdIn(postIds)
}
