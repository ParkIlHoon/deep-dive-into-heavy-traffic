package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.entity.timeline

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.entity.UUIDPrimaryKey
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "timeline")
@Comment("타임라인")
class Timeline(
    memberId: UUID,
    postId: UUID,
    postedAt: LocalDateTime,
) : UUIDPrimaryKey() {
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    @Comment("회원 아이디")
    var memberId: UUID = memberId
        protected set

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    @Comment("포스트 아이디")
    var postId: UUID = postId
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
