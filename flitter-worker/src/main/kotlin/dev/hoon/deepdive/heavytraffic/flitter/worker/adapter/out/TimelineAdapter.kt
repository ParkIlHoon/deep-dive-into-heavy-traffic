package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.TimelineRepository
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign.TimelineClient
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.TimelinePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TimelineAdapter(
    private val timelineClient: TimelineClient,
    private val timelineRepository: TimelineRepository,
) : TimelinePort {
    @Transactional
    override fun createTimelines(timelines: List<TimelineDto.CreateRequest>) {
        timelineClient.create(timelines)
    }

    override fun deleteAllByPostId(postId: UUID) =
        timelineRepository.deleteAllByPostId(postId)

    override fun deleteAllByMemberId(memberId: UUID) =
        timelineRepository.deleteAllByMemberId(memberId)

    @Transactional
    override fun deleteAllByPostIdIn(postIds: List<UUID>) = timelineRepository.deleteAllByPostIdIn(postIds)
}
