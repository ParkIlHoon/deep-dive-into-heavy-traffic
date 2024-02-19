package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotFollowException
import java.util.*

/**
 * 팔로잉 유스 케이스
 */
fun interface FollowingUseCase {
    /**
     * 팔로우
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 대상 회원 아이디
     * @throws CannotFollowException 팔로우 실패 시
     */
    @Throws(CannotFollowException::class)
    fun follow(followerId: UUID, followId: UUID)
}
