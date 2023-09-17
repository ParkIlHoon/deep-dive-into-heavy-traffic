package dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.timeline

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.UUIDPrimaryKey
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "timeline")
@Comment("타임라인")
class Timeline(
    memberId: String,
    postId: String,
    postedAt: LocalDateTime
): UUIDPrimaryKey() {
    @Column(nullable = false)
    @Comment("회원 아이디")
    var memberId: String = memberId
        protected set

    @Column(nullable = false)
    @Comment("포스트 아이디")
    var postId: String = postId
        protected set

    @Column(nullable = false)
    @Comment("포스트 일시")
    var postedAt: LocalDateTime = postedAt
        protected set

    @Column(nullable = false)
    @Comment("생성일시")
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}