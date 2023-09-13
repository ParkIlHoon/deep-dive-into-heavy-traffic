package dev.hoon.deepdive.heavytraffic.flitter.domain.timeline

import java.time.LocalDateTime

/**
 * 타임라인
 *
 * @property id 아이디
 * @property memberId 회원 아이디
 * @property postId 포스트 아이디
 * @property postedAt 포스팅 일시
 * @property createdAt 생성일시
 */
class Timeline(
    val id: Long? = null,
    val memberId: Long,
    val postId: Long,
    val postedAt: LocalDateTime,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
}