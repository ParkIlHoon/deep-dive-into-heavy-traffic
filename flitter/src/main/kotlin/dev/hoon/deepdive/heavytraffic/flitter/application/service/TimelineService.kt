package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.ReadTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MemberPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.TimelinePersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TimelineService(
    private val timelinePersistencePort: TimelinePersistencePort,
    private val postPersistencePort: PostPersistencePort,
    private val memberPersistencePort: MemberPersistencePort
): ReadTimelineUseCase {
    override fun read(memberId: Long, cursor: CursorRequest): CursorResponse<TimelineDto.Response> {
        val timelines = timelinePersistencePort.findAllByMemberId(memberId, cursor)
        val posts = postPersistencePort.findAllByIdIn(timelines.body.map { memberId })
        val writers = memberPersistencePort.findAllByIdIn(posts.map { memberId }).toSet()

        val timelineResp = timelines.body.map {
            val post = posts.first { post -> post.id == it.postId }
            val writer = writers.first { writer -> writer.id == post.memberId }

            TimelineDto.Response.of(it, post, writer)
        }
        return CursorResponse(timelines.next, timelineResp)
    }
}