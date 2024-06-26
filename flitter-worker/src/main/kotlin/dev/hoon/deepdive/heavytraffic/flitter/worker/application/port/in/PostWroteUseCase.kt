@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`

import java.time.LocalDateTime
import java.util.UUID

/**
 * 포스트 작성 후처리
 */
fun interface PostWroteUseCase {
    /**
     * 실행
     *
     * 1. 작성자의 팔로워의 타임라인에 작성된 포스트 추가
     *
     * @param postId 작성된 포스트 아이디
     * @param writerId 작성자 회원 아이디
     * @param postedAt 포스팅 일시
     */
    fun afterPostWrote(
        postId: UUID,
        writerId: UUID,
        postedAt: LocalDateTime,
    )
}
