package dev.hoon.deepdive.heavytraffic.flitter.domain.member

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import jakarta.persistence.* // ktlint-disable no-wildcard-imports
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "member_nickname_history")
@Comment("회원 닉네임 이력")
class NicknameHistory(
    member: Member,
    nickname: String,
) : UUIDPrimaryKey() {
    @ManyToOne(
        targetEntity = Member::class,
        fetch = FetchType.LAZY,
        optional = false,
    )
    @JoinColumn(name = "member_id")
    @Comment("회원 아이디")
    var member: Member = member
        protected set

    @Column(nullable = false)
    @Comment("기존 닉네임")
    var nickname: String = nickname
        protected set

    @Column(nullable = false)
    @Comment("생성일시")
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}
