package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotWritePostException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.internal.AfterWritePostProcessor
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.TimelinePort
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.timeline.Timeline
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional(readOnly = true)
class AfterWritePostService(
    private val postPort: PostPort,
    private val memberPort: MemberPort,
    private val followPort: FollowPort,
    private val timelinePort: TimelinePort,
) : AfterWritePostProcessor {
    @Transactional
    override fun execute(postId: UUID, writerId: UUID, postedAt: LocalDateTime) {
        // 1. 포스트와 작성자 유효성 검증
        validatePost(postId) { CannotWritePostException(it) }
        validateMember(writerId) { CannotWritePostException(it) }

        // 2. 작성자 팔로워 조회
        val followerIds = followPort.getByFollowMemberId(writerId).map { it.followerMemberId }

        // 3. 타임라인 생성
        val timelines = followerIds.map { Timeline(memberId = it, postId = postId, postedAt = postedAt) }
        timelinePort.saveAll(timelines)
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }

    private fun validatePost(postId: UUID, thrower: (Exception) -> Exception) {
        try {
            postPort.get(postId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}