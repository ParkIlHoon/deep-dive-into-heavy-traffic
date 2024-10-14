@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out

import java.time.LocalDateTime
import java.util.*

/**
 * 메시지 큐 포트
 */
interface MessageQueuePort {
    /**
     * 포스트 작성 이벤트 발행
     *
     * @param post 작성된 포스트
     */
    fun publishPostWroteEvent(
        postId: UUID,
        writerId: UUID,
        postedAt: LocalDateTime,
    )

    /**
     * 팔로우 이벤트 발행
     *
     * @param followerMemberId 팔로워 회원 아이디
     * @param memberId 팔로우 대상 회원 아이디
     */
    fun publishFollowEvent(
        followerMemberId: UUID,
        memberId: UUID,
    )

    /**
     * 언팔로우 이벤트 발행
     *
     * @param followerMemberId 팔로워 회원 아이디
     * @param memberId 언팔로우 대상 회원 아이디
     */
    fun publishUnFollowEvent(
        followerMemberId: UUID,
        memberId: UUID,
    )

    /**
     * 회원 탈퇴 이벤트 발행
     *
     * @param memberId 탈퇴 회원 아이디
     */
    fun publishMemberLeaveEvent(memberId: UUID)

    /**
     * 포스트 좋아요 이벤트 발행
     *
     * @param postId 좋아요를 누른 포스트 아이디
     * @param memberId 좋아요을 누른 멤버 아이디
     * @param likedAt 좋아요를 누른 시간
     */
    fun publishPostLikeEvent(
        postId: UUID,
        memberId: UUID,
        likedAt: LocalDateTime,
    )
}
