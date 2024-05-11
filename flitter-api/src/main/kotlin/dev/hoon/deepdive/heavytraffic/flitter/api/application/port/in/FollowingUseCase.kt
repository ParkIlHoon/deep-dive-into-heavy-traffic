@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.AlreadyFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotFollowException
import java.util.*

/**
 * 팔로잉 유스 케이스
 */
fun interface FollowingUseCase {
    /**
     * 팔로우
     *
     * @param followerMemberId 팔로워 회원 아이디
     * @param memberId 팔로우 대상 회원 아이디
     * @throws AlreadyFollowException 이미 팔로우 중일 경우
     * @throws CannotFollowException 팔로우 실패 시
     */
    @Throws(AlreadyFollowException::class, CannotFollowException::class)
    fun follow(
        followerMemberId: UUID,
        memberId: UUID,
    )
}
