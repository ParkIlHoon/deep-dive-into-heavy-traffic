package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out

import java.util.*

/**
 * 포스트 좋아요 포트
 */
interface PostLikePort {
    /**
     * 삭제
     *
     * @param memberId 회원 아이디
     */
    fun deleteByMemberId(memberId: UUID)
}
