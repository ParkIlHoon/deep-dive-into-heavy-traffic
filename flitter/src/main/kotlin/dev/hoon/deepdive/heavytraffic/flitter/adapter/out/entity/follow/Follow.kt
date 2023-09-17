package dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.follow

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.UUIDPrimaryKey
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "follow")
@Comment("팔로우")
class Follow(
    memberId: String,
    followerMemberId: String
): UUIDPrimaryKey() {
    @Column(nullable = false)
    @Comment("회원 아이디")
    var memberId: String = memberId
        protected set

    @Column(nullable = false)
    @Comment("팔로워 회원 아이디")
    var followerMemberId: String = followerMemberId
        protected set

    @Column(nullable = false)
    @Comment("생성일시")
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}