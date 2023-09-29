package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.internal // ktlint-disable package-name

import java.util.UUID

/**
 * 팔로우 후처리
 */
fun interface AfterFollowProcessor {
    /**
     * 실행
     *
     * 1. 팔로우한 회원이 작성한 포스트를 팔로워의 타임라인에 추가
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 회원 아이디
     */
    fun execute(followerId: UUID, followId: UUID)
}