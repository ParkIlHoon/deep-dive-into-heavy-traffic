package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotDeletePostLikeException
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike

/**
 * 포스트 좋아요 영속성 포트
 */
interface PostLikePersistencePort {
    /**
     * 저장
     *
     * @param postLike 저장할 포스트 좋아요
     * @return 저장된 포스트 좋아요
     */
    fun save(postLike: PostLike): PostLike

    /**
     * 삭제
     *
     * @param postId 포스트 아이디
     * @param memberId 회원 아이디
     * @throws CannotDeletePostLikeException 포스트 좋아요가 존재하지 않거나 삭제에 실패할 경우
     */
    @Throws(CannotDeletePostLikeException::class)
    fun delete(postId: Long, memberId: Long)
    /**
     * 카운트
     *
     * @param postId 포스트 아이디
     * @return 포스트 좋아요 수(최소 0)
     */
    fun count(postId: Long): Long
}