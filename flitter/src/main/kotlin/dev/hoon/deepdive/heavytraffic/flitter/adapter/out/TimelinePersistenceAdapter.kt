package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper.TimelineMapper
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.TimelineRepository
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
    @Transactional
    override fun save(timeline: Timeline): Timeline =
        TimelineMapper.toEntity(timeline)
            .let { timelineRepository.save(it) }
            .let { TimelineMapper.toDomain(it) }

    @Transactional
    override fun saveAll(timelines: List<Timeline>): List<Timeline> =
        timelines.map { TimelineMapper.toEntity(it) }
            .run { timelineRepository.saveAll(this) }
            .map { TimelineMapper.toDomain(it) }

    override fun findAllByLessThanIdAndMemberIdAndOrderByIdDesc(timelineId: UUID, memberId: UUID, size: Long): List<Timeline> =
        timelineRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(timelineId, memberId, size)
            .map { TimelineMapper.toDomain(it) }

    override fun findAllByMemberIdAndOrderByIdDesc(memberId: UUID, size: Long): List<Timeline> =
        timelineRepository.findAllByMemberIdAndOrderByIdDesc(memberId, size)
            .map { TimelineMapper.toDomain(it) }

    override fun findAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>): List<Timeline> =
        timelineRepository.findAllByMemberIdAndPostIdIn(memberId, postIds)
            .map { TimelineMapper.toDomain(it) }

    @Transactional
    override fun deleteAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>) =
        timelineRepository.deleteAllByMemberIdAndPostIdIn(memberId, postIds)
}