@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.service

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.CannotWritePostException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.PostWroteUseCase
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.TimelinePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional(readOnly = true)
class PostService(
    private val postPort: PostPort,
    private val memberPort: MemberPort,
    private val followPort: FollowPort,
    private val timelinePort: TimelinePort,
) : PostWroteUseCase {
    @Transactional
    override fun afterPostWrote(
        postId: UUID,
        writerId: UUID,
        postedAt: LocalDateTime,
    ) {
        // 1. 포스트와 작성자 유효성 검증
        validatePost(postId) { CannotWritePostException(it) }
        validateMember(writerId) { CannotWritePostException(it) }

        // 2. 작성자 팔로워 조회
        val followerIds = followPort.getByFollowMemberId(writerId).map { it.followerMemberId }

        // 3. 타임라인 생성
        val timelines = followerIds.map { TimelineDto.CreateRequest(memberId = it, postId = postId, postedAt = postedAt) }
        timelinePort.createTimelines(timelines)
    }

    private fun validateMember(
        memberId: UUID,
        thrower: (Exception) -> Exception,
    ) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }

    private fun validatePost(
        postId: UUID,
        thrower: (Exception) -> Exception,
    ) {
        try {
            postPort.get(postId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}
