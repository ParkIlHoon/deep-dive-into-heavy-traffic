package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import java.util.*

/**
 * 포스트 좋아요 포트
 */
interface PostLikePort {
    /**
     * 저장
     *
     * @param postLike 저장할 포스트 좋아요
     * @return 저장된 포스트 좋아요
     */
    fun create(postLike: PostLike): PostLike

    /**
     * 삭제
     *
     * @param postLike 삭제할 포스트 좋아요
     */
    fun delete(postLike: PostLike)

    /**
     * 특정 회원의 전체 좋아요 삭제
     */
    fun deleteAllByMember(memberId: UUID): Long
}
