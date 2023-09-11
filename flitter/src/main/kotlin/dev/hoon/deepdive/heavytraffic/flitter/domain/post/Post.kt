package dev.hoon.deepdive.heavytraffic.flitter.domain.post

import java.time.LocalDateTime

/**
 * 포스트
 *
 * @property id 아이디
 * @property memberId 작성 회원 아이디
 * @property contents 내용
 * @property like 좋아요 수
 * @property createdAt 생성일시
 */
class Post(
    val id: Long? = null,
    val memberId: Long,
    var contents: String,
    var like: Long = 0L,
    val createdAt: LocalDateTime = LocalDateTime.now()
)