package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostLikeRepository : JpaRepository<PostLike, UUIDPrimaryKey> {
    fun delete(spec: Specification<PostLike>): Long
}
