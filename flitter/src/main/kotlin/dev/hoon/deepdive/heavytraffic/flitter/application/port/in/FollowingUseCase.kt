package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotFollowException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotUnFollowException

/**
 * 팔로잉 유스 케이스
 */
interface FollowingUseCase {
    /**
     * 팔로우
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 대상 회원 아이디
     * @throws CannotFollowException 팔로우 실패 시
     */
    @Throws(CannotFollowException::class)
    fun follow(followerId: Long, followId: Long)
    /**
     * 언팔로우
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 중인 회원 아이디
     * @throws CannotUnFollowException 언팔로우 실패 시
     */
    @Throws(CannotUnFollowException::class)
    fun unFollow(followerId: Long, followId: Long)
}