@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`

import java.util.UUID

/**
 * 언팔로우 유스 케이스
 */
fun interface UnFollowedUseCase {
    /**
     * 실행
     *
     * 1. 팔로워 회원의 타임라인에서 언팔로우한 회원이 작성한 포스트 제거
     *
     * @param followerMemberId 팔로워 회원 아이디
     * @param memberId 팔로우 회원 아이디
     */
    fun afterUnFollowed(
        followerMemberId: UUID,
        memberId: UUID,
    )
}
