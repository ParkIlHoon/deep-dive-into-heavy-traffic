package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotUnFollowException
import java.util.*

/**
 * 언팔로잉 유스 케이스
 */
fun interface UnFollowingUseCase {
    /**
     * 언팔로우
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 중인 회원 아이디
     * @throws CannotUnFollowException 언팔로우 실패 시
     */
    @Throws(CannotUnFollowException::class)
    fun unFollow(followerId: UUID, followId: UUID)
}