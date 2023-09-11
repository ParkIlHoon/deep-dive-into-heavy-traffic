package dev.hoon.deepdive.heavytraffic.flitter.domain.member

import java.time.LocalDateTime

/**
 * 닉네임 히스토리
 *
 * @property id 아이디
 * @property member 회원
 * @property nickname 닉네임
 * @property createdAt 생성일시
 */
class NicknameHistory(
    val id: Long? = null,
    val member: Member,
    val nickname: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)