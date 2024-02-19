package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.mapper

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.entity.post.Post as PostEntity
import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.entity.post.PostLike as PostLikeEntity
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.post.Post as PostDomain
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.post.PostLike as PostLikeDomain

object PostMapper {

    fun toEntity(domain: PostDomain): PostEntity =
        PostEntity(
            writerId = domain.writerId,
            contents = domain.contents,
        ).apply {
            setId(domain.id)
        }

    fun toDomain(entity: PostEntity): PostDomain =
        PostDomain(
            id = entity.id,
            writerId = entity.writerId,
            contents = entity.contents,
            like = entity.like,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )

    fun toEntity(domain: PostLikeDomain): PostLikeEntity =
        PostLikeEntity(
            post = toEntity(domain.post),
            memberId = domain.memberId,
        ).apply {
            setId(domain.id)
        }

    fun toDomain(entity: PostLikeEntity): PostLikeDomain =
        PostLikeDomain(
            id = entity.id,
            post = toDomain(entity.post),
            memberId = entity.memberId,
            createdAt = entity.createdAt,
        )
}
