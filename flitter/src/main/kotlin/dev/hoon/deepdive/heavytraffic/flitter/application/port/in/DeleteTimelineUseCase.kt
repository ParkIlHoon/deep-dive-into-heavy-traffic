package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in` // ktlint-disable package-name

import java.util.*

/**
 * 타임라인 삭제 유스케이스
 */
interface DeleteTimelineUseCase {
    /**
     * 언팔로우로 인한 동작
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 대상 회원 아이디
     */
    fun deleteByUnFollow(followerId: UUID, followId: UUID)
}
