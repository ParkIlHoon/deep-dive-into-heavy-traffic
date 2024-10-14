@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`

import java.time.LocalDateTime
import java.util.UUID

/**
 * 포스트 좋아요 후처리
 */
fun interface PostLikedUseCase {
    /**
     * 실행
     *
     * 1. 포스트 작성자에게 알림 전송
     *
     * @param postId 좋아요가 눌린 포스트 아이디
     * @param memberId 좋아요를 누른 회원 아이디
     * @param likedAt 좋아요를 누른 일시
     */
    fun afterPostLiked(
        postId: UUID,
        memberId: UUID,
        likedAt: LocalDateTime,
    )
}
