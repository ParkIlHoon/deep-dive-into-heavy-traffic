@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.CannotFollowException
import java.util.UUID

/**
 * 팔로우 유스 케이스
 */
fun interface FollowedUseCase {
    /**
     * 팔로우 후처리 작업 실행
     *
     * 1. 팔로우 대상이 작성한 포스트를 팔로워의 타임라인에 추가
     *
     * @param followerMemberId 팔로워 회원 아이디
     * @param memberId 팔로우 대상 아이디
     * @throws CannotFollowException 팔로우 후처리 작업 실패 시
     */
    @Throws(CannotFollowException::class)
    fun afterFollowed(
        followerMemberId: UUID,
        memberId: UUID,
    )
}
