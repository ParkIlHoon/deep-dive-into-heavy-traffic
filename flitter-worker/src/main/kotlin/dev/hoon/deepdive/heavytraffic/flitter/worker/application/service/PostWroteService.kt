@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.service

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
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
class PostWroteService(
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
        val post = postPort.get(id = postId)
        val writer = memberPort.get(memberId = writerId)

        // 2. 작성자 팔로워 조회
        val followerIds = followPort.getByFollowMemberId(writer.id).map { it.followerMemberId }

        // 3. 타임라인 생성
        val timelines = followerIds.map { TimelineDto.CreateRequest(memberId = it, postId = post.id, postedAt = post.createdAt) }
        timelinePort.createTimelines(timelines)
    }
}
