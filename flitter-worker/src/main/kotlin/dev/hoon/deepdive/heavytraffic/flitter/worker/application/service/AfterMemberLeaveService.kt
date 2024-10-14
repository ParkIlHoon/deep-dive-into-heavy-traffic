@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.service

import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.AfterMemberLeaveProcessor
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.TimelinePort
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
        memberId
            .also(this::deleteFollowerTimelines)
            .also(this::deleteFollowing)
            .also(this::deletePostLike)
            .also(this::deletePost)
            .also(this::deleteTimeline)
    }

    private fun deleteFollowerTimelines(memberId: UUID) =
        postPort.getByWriter(memberId)
            .mapNotNull { it.id }
            .let { timelinePort.deleteAllByPostIdIn(it) }

    private fun deleteFollowing(memberId: UUID) = followPort.delete(memberId)

    private fun deletePostLike(memberId: UUID) = postLikePort.deleteAllByMemberId(memberId)

    private fun deletePost(memberId: UUID) = postPort.deleteByWriter(memberId)

    private fun deleteTimeline(memberId: UUID) = timelinePort.deleteAllByMemberId(memberId)
}
