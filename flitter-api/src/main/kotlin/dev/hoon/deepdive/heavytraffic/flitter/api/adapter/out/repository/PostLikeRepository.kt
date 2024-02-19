package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PostLikeRepository : JpaRepository<PostLike, UUIDPrimaryKey> {

    fun countByPostId(postId: UUID): Long
    fun deleteAllByMemberId(memberId: UUID)
}
