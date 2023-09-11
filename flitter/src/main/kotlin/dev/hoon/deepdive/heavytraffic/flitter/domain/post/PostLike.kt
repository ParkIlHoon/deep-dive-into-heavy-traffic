package dev.hoon.deepdive.heavytraffic.flitter.domain.post

import java.time.LocalDateTime

/**
 * 포스트 좋아요
 *
 * @property post 포스트
 * @property memberId 회원 아이디
 * @property createdAt 생성일시
 */
class PostLike(
    val post: Post,
    val memberId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now()
)