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
    @Transactional
    override fun save(timeline: Timeline): Timeline = timelineRepository.save(timeline)

    @Transactional
    override fun saveAll(timelines: List<Timeline>): List<Timeline> = timelineRepository.saveAll(timelines)

    override fun get(timelineId: UUID, memberId: UUID, size: Long): List<Timeline> =
        timelineRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(timelineId, memberId, size)

    override fun get(memberId: UUID, size: Long): List<Timeline> =
        timelineRepository.findAllByMemberIdAndOrderByIdDesc(memberId, size)

    override fun get(memberId: UUID): List<Timeline> =
        timelineRepository.findAllByMemberId(memberId)

    override fun findAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>): List<Timeline> =
        timelineRepository.findAllByMemberIdAndPostIdIn(memberId, postIds)

    @Transactional
    override fun deleteAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>) =
        timelineRepository.deleteAllByMemberIdAndPostIdIn(memberId, postIds)

    override fun deleteAllByPostId(postId: UUID) =
        timelineRepository.deleteAllByPostId(postId)

    override fun deleteAllByMemberId(memberId: UUID) =
        timelineRepository.deleteAllByMemberId(memberId)

    @Transactional
    override fun deleteAllByPostIdIn(postIds: List<UUID>) = timelineRepository.deleteAllByPostIdIn(postIds)
}
