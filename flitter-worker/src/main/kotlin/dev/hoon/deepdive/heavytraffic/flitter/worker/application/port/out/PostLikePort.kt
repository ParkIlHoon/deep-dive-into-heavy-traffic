@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out

import java.util.*

/**
 * 포스트 좋아요 포트
 */
@SuppressWarnings("kotlin:S6517")
interface PostLikePort {
    /**
     * 삭제
     *
     * @param memberId 회원 아이디
     */
    fun deleteAllByMemberId(memberId: UUID)
}
