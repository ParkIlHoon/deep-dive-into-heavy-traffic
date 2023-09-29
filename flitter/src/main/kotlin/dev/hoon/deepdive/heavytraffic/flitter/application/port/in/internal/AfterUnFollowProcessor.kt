package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.internal // ktlint-disable package-name

import java.util.UUID

/**
 * 팔로우 취소 후처리
 */
fun interface AfterUnFollowProcessor {
    /**
     * 실행
     *
     * 1. 팔로워 회원의 타임라인에서 언팔로우한 회원이 작성한 포스트 제거
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 회원 아이디
     */
    fun execute(followerId: UUID, followId: UUID)
}