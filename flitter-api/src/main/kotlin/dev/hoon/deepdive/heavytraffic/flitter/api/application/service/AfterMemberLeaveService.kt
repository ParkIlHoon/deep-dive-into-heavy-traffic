package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.internal.AfterMemberLeaveProcessor
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.TimelinePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class AfterMemberLeaveService(
    private val postPort: PostPort,
    private val timelinePort: TimelinePort,
    private val followPort: FollowPort,
    private val postLikePort: PostLikePort,
) : AfterMemberLeaveProcessor {
    @Transactional
    override fun execute(memberId: UUID) {
        // 1. 탈퇴 회원의 작성글을 참조하는 타임라인 제거
        deleteFollowerTimelines(memberId)

        // 2. 탈퇴 회원을 팔로우하는 팔로잉 제거
        deleteFollowing(memberId)

        // 3. 탈퇴 회원이 누른 좋아요 제거
        deletePostLike(memberId)

        // 4. 탈퇴 회원의 작성글 제거
        deletePost(memberId)

        // 5. 탈퇴 회원의 타임라인 제거
        deleteTimeline(memberId)
    }

    private fun deleteFollowerTimelines(memberId: UUID) {
        postPort.getByWriter(memberId)
            .mapNotNull { it.id }
            .let { timelinePort.deleteAllByPostIdIn(it) }
    }

    private fun deleteFollowing(memberId: UUID) = followPort.delete(memberId)

    private fun deletePostLike(memberId: UUID) = postLikePort.deleteByMemberId(memberId)

    private fun deletePost(memberId: UUID) = postPort.deleteByWriter(memberId)

    private fun deleteTimeline(memberId: UUID) = timelinePort.deleteAllByMemberId(memberId)
}