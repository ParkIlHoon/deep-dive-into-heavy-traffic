package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotReadTimelineException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.CreateTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.DeleteTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.ReadTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.TimelinePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class TimelineService(
    private val timelinePort: TimelinePort,
    private val postPort: PostPort,
    private val memberPort: MemberPort,
) : CreateTimelineUseCase, ReadTimelineUseCase, DeleteTimelineUseCase {
    override fun read(memberId: UUID, cursor: CursorRequest): CursorResponse<TimelineDto.Response> {
        validateMember(memberId) { CannotReadTimelineException(it) }

        val timelines = getTimelineWithCursor(memberId, cursor)
        val posts = postPort.get(timelines.body.map { memberId })
        val writers = memberPort.get(posts.map { memberId }).toSet()

        val timelineResp = timelines.body.map {
            val post = posts.first { post -> post.id == it.postId }
            val writer = writers.first { writer -> writer.id == post.writerId }

            TimelineDto.Response.of(it, post, writer)
        }
        return CursorResponse(timelines.next, timelineResp)
    }

    private fun getTimelineWithCursor(memberId: UUID, cursor: CursorRequest): CursorResponse<Timeline> {
        validateMember(memberId) { CannotReadTimelineException(it) }

        val timelines = if (cursor.hasKey()) {
            timelinePort.get(cursor.key, memberId, cursor.size)
        } else {
            timelinePort.get(memberId, cursor.size)
        }

        val nextKey = timelines.minBy { it.id.toString() }.id
        return CursorResponse(cursor.next(nextKey), timelines)
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }

    @Transactional
    override fun create(createRequests: List<TimelineDto.CreateRequest>) {
        timelinePort.create(createRequests.map { Timeline(it.memberId, it.postId, it.postedAt) })
    }

    @Transactional
    override fun delete(deleteTargets: List<TimelineDto.DeleteRequest>) {
        timelinePort.delete(deleteTargets)
    }
}
