package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.internal.AfterFollowProcessor
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.TimelinePort
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.timeline.Timeline
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class AfterFollowService(
    private val memberPort: MemberPort,
    private val postPort: PostPort,
    private val timelinePort: TimelinePort,
) : AfterFollowProcessor {

    @Transactional
    override fun execute(followerId: UUID, followId: UUID) {
        // 1. 회원 유효성 체크
        validateMember(followerId) { CannotFollowException(it) }
        validateMember(followId) { CannotFollowException(it) }

        // 2. 팔로우 회원 포스트 조회
        val posts = postPort.getByWriter(followId)

        // 3. 팔로워의 타임라인에 포스트 추가
        val timelines = posts.map { Timeline(memberId = followerId, postId = it.id!!, postedAt = it.createdAt) }
        timelinePort.saveAll(timelines)
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}