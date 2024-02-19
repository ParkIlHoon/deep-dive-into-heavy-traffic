package dev.hoon.deepdive.heavytraffic.flitter.domain.follow

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "follow")
@Comment("팔로우")
class Follow(
    memberId: UUID,
    followerMemberId: UUID,
) : UUIDPrimaryKey() {

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    @Comment("회원 아이디")
    var memberId: UUID = memberId
        protected set

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    @Comment("팔로워 회원 아이디")
    var followerMemberId: UUID = followerMemberId
        protected set

    @Column(nullable = false)
    @Comment("생성일시")
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}
