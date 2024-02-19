package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PostRepository : JpaRepository<Post, UUIDPrimaryKey> {
    fun findById(id: UUID): Post?

    fun findAllByIdIn(ids: List<UUID>): List<Post>

    fun findAllByWriterId(writerId: UUID): List<Post>

    fun deleteById(id: UUID)
    fun deleteAllByWriterId(writerId: UUID)
}
