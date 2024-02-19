package dev.hoon.deepdive.heavytraffic.flitter.domain.member

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import jakarta.persistence.* // ktlint-disable no-wildcard-imports
import org.hibernate.annotations.Comment
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "member")
@Comment("회원")
class Member(
    nickname: String,
    email: String,
    birthDay: LocalDate,
) : UUIDPrimaryKey() {
    @Column(nullable = false)
    @Comment("닉네임")
    var nickname: String = nickname
        protected set

    @Column(nullable = false)
    @Comment("이메일")
    var email: String = email
        protected set

    @Column(nullable = false)
    @Comment("생일")
    var birthDay: LocalDate = birthDay
        protected set

    @Column(nullable = false)
    @Comment("생성일시")
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(nullable = false)
    @Comment("수정일시")
    var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        mappedBy = "member",
    )
    protected val mutableNicknameHistories: MutableList<NicknameHistory> = mutableListOf()
    val nicknameHistories: List<NicknameHistory> get() = mutableNicknameHistories.toList()

    /**
     * 닉네임을 변경합니다.
     *
     * @param nickname 변경할 닉네임
     * @return 기존 닉네임 히스토리
     */
    fun changeNickname(nickname: String): NicknameHistory {
        val nicknameHistory = NicknameHistory(
            member = this,
            nickname = this.nickname,
        )
        this.nickname = nickname
        this.updatedAt = LocalDateTime.now()
        return nicknameHistory
    }

    fun changeBirthday(newBirthday: LocalDate) {
        this.birthDay = newBirthday
        this.updatedAt = LocalDateTime.now()
    }
}
