@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.service

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.CannotFollowException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.FollowedUseCase
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.TimelinePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class FollowedService(
    private val memberPort: MemberPort,
    private val postPort: PostPort,
    private val timelinePort: TimelinePort,
) : FollowedUseCase {
    @Transactional
    override fun afterFollowed(
        followerMemberId: UUID,
        memberId: UUID,
    ) {
        // 1. 회원 유효성 체크
        val follower = memberPort.get(followerMemberId)
        val member = memberPort.get(memberId)

        try {
            // 2. 팔로우 대상 회원 포스트 조회
            postPort.getByWriter(member.id)
                .map { TimelineDto.CreateRequest(memberId = follower.id, postId = it.id, postedAt = it.createdAt) }
                .let {
                    // 3. 팔로워의 타임라인에 포스트 추가
                    timelinePort.createTimelines(it)
                }
        } catch (e: Exception) {
            throw CannotFollowException(e)
        }
    }
}
