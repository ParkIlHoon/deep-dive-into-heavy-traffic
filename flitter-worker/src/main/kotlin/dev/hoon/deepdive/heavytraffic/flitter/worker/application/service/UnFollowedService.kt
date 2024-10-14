@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.service

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.CannotUnFollowException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.UnFollowedUseCase
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.TimelinePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class UnFollowedService(
    private val memberPort: MemberPort,
    private val postPort: PostPort,
    private val timelinePort: TimelinePort,
) : UnFollowedUseCase {
    @Transactional
    override fun afterUnFollowed(
        followerMemberId: UUID,
        memberId: UUID,
    ) {
        // 1. 팔로워 유효성 체크
        val follower = memberPort.get(followerMemberId)
        val member = memberPort.get(memberId)

        try {
            // 2. 언팔로우 대상 회원 포스트 조회
            postPort.getByWriter(member.id)
                .map { TimelineDto.DeleteRequest(memberId = follower.id, postId = it.id) }
                .let {
                    // 3. 타임라인에서 해당 포스트 제거
                    timelinePort.deleteTimelines(it)
                }
        } catch (e: Exception) {
            throw CannotUnFollowException(e)
        }
    }
}
