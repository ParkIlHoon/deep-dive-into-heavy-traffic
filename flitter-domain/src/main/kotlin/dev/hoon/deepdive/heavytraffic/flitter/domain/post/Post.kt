@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.domain.post

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "post")
@Comment("포스트")
class Post(
    writerId: UUID,
    contents: String,
) : UUIDPrimaryKey() {
    @Column(nullable = false, columnDefinition = "BINARY(16)")
    @Comment("작성자 아이디")
    var writerId: UUID = writerId
        protected set

    @Column(nullable = false)
    @Comment("내용")
    var contents: String = contents
        protected set

    @Column(nullable = false, name = "like_count")
    @Comment("좋아요 수")
    var like: Long = 0L
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
        mappedBy = "post",
    )
    protected val mutableLikes: MutableList<PostLike> = mutableListOf()
    val likes: List<PostLike> get() = mutableLikes.toList()

    fun updateContents(newContents: String) {
        this.contents = newContents
        this.updatedAt = LocalDateTime.now()
    }
}
