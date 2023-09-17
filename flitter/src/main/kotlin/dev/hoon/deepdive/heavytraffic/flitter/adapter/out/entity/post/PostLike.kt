package dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.post

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.UUIDPrimaryKey
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Table(name = "post_like")
@Comment("포스트 좋아요")
class PostLike(
    post: Post,
    memberId: String
): UUIDPrimaryKey() {
    @ManyToOne(
        targetEntity = Post::class,
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(name = "post_id")
    @Comment("포스트 아이디")
    var post: Post = post
        protected set

    @Column(nullable = false)
    @Comment("좋아요를 누른 회원 아이디")
    var memberId: String = memberId
        protected set

    @Column(nullable = false)
    @Comment("생성일시")
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}