package dev.hoon.deepdive.heavytraffic.flitter.domain.member

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 회원
 *
 * @property id 아이디
 * @property nickname 닉네임
 * @property email 이메일
 * @property birthDay 생일
 * @property createdAt 생성일시
 */
class Member(
    val id: Long? = null,
    var nickname: String,
    val email: String,
    val birthDay: LocalDate,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    /**
     * 닉네임을 변경합니다.
     *
     * @param nickname 변경할 닉네임
     * @return 기존 닉네임 히스토리
     */
    fun changeNickname(nickname: String): NicknameHistory {
        val nicknameHistory = NicknameHistory(
            member = this,
            nickname = this.nickname
        )
        this.nickname = nickname
        return nicknameHistory
    }
}