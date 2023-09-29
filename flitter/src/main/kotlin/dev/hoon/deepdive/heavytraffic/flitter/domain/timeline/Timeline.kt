package dev.hoon.deepdive.heavytraffic.flitter.domain.timeline

import java.time.LocalDateTime
import java.util.*

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
    val id: UUID? = null,
    val memberId: UUID,
    val postId: UUID,
    val postedAt: LocalDateTime,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
