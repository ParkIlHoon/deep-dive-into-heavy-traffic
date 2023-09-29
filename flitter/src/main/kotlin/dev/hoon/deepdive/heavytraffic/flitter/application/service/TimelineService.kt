package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotReadTimelineException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.ReadTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.TimelinePort
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
) : ReadTimelineUseCase {
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

        val nextKey = timelines.minBy { it.id.toString() }.id ?: CursorRequest.NO_KEY
        return CursorResponse(cursor.next(nextKey), timelines)
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}
