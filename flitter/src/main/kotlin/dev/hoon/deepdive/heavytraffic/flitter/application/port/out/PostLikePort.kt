package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

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
     * 카운트
     *
     * @param postId 포스트 아이디
     * @return 포스트 좋아요 수(최소 0)
     */
    fun count(postId: UUID): Long

    /**
     * 삭제
     *
     * @param memberId 회원 아이디
     */
    fun deleteByMemberId(memberId: UUID)
}
