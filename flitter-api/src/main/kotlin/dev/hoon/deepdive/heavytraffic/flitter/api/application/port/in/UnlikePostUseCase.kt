@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotUnLikePostException
import java.util.*

interface UnlikePostUseCase {
    /**
     * 좋아요 취소
     *
     * @param memberId 회원 아이디
     * @param postId 좋아요 취소할 포스트 아이디
     * @throws CannotUnLikePostException 좋아요 취소 실패 시
     */
    @Throws(CannotUnLikePostException::class)
    fun unLike(
        memberId: UUID,
        postId: UUID,
    )

    @Throws(CannotUnLikePostException::class)
    fun unLikeAllByMember(memberId: UUID)
}
