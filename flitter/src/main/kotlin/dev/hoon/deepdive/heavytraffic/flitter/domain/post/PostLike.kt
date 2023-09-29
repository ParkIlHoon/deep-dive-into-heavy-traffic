package dev.hoon.deepdive.heavytraffic.flitter.domain.post

import java.time.LocalDateTime
import java.util.*

/**
 * 포스트 좋아요
 *
 * @property post 포스트
 * @property memberId 회원 아이디
 * @property createdAt 생성일시
 */
class PostLike(
    val id: UUID? = null,
    val post: Post,
    val memberId: UUID,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
