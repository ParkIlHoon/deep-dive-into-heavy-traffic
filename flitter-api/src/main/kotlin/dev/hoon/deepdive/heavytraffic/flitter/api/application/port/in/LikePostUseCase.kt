package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotLikePostException
import java.util.*

/**
 * 포스트 좋아요 유스 케이스
 */
fun interface LikePostUseCase {
    /**
     * 좋아요
     *
     * @param memberId 회원 아이디
     * @param postId 좋아요할 포스트 아이디
     * @throws CannotLikePostException 좋아요 실패 시
     */
    @Throws(CannotLikePostException::class)
    fun like(memberId: UUID, postId: UUID)
}
