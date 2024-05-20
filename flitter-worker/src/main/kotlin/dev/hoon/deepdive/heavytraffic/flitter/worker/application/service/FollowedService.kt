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
        validateMember(followerMemberId) { CannotFollowException("유효하지 않은 팔로워입니다.", it) }
        validateMember(memberId) { CannotFollowException("유효하지 않은 팔로우 대상입니다.", it) }

        try {
            // 2. 팔로우 대상 회원 포스트 조회
            val timelines =
                postPort.getByWriter(memberId)
                    .map { TimelineDto.CreateRequest(memberId = followerMemberId, postId = it.id, postedAt = it.createdAt) }

            // 3. 팔로워의 타임라인에 포스트 추가
            timelinePort.createTimelines(timelines)
        } catch (e: Exception) {
            throw CannotFollowException(e)
        }
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
}
