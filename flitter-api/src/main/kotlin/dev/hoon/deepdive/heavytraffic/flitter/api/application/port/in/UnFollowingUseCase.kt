@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotUnFollowException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.NotFollowException
import java.util.*

/**
 * 언팔로잉 유스 케이스
 */
fun interface UnFollowingUseCase {
    /**
     * 언팔로우
     *
     * @param followerMemberId 팔로워 회원 아이디
     * @param memberId 팔로우 중인 회원 아이디
     * @throws NotFollowException 팔로우 중이 아닐 경우
     * @throws CannotUnFollowException 언팔로우 실패 시
     */
    @Throws(NotFollowException::class, CannotUnFollowException::class)
    fun unFollow(
        followerMemberId: UUID,
        memberId: UUID,
    )
}
