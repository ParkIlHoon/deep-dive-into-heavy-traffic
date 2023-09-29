package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in` // ktlint-disable package-name

import java.util.*

/**
 * 타임라인 생성 유스케이스
 */
interface CreateTimelineUseCase {
    /**
     * 포스트 작성으로 인한 동작
     *
     * @param postId 작성된 포스트 아이디
     * @param writerId 작성자 회원 아이디
     */
    fun createByPost(postId: UUID, writerId: UUID)

    /**
     * 팔로우로 인한 동작
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 대상 회원 아이디
     */
    fun createByFollow(followerId: UUID, followId: UUID)
}
