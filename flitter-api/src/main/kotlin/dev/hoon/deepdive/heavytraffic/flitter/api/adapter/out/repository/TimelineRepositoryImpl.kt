package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.QTimeline.timeline
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TimelineRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : TimelineCustomRepository {
    override fun findAllByLessThanIdAndMemberIdAndOrderByIdDesc(timelineId: UUID, memberId: UUID, size: Long): List<Timeline> =
        jpaQueryFactory.selectFrom(timeline)
            .where(
                timeline.memberId.eq(memberId)
                    .and(timeline.id.lt(timelineId)),
            )
            .orderBy(timeline.id.desc())
            .limit(size)
            .fetch()

    override fun findAllByMemberIdAndOrderByIdDesc(memberId: UUID, size: Long): List<Timeline> =
        jpaQueryFactory.selectFrom(timeline)
            .where(
                timeline.memberId.eq(memberId),
            )
            .orderBy(timeline.id.desc())
            .limit(size)
            .fetch()
}
