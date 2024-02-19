package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.mapper

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.entity.follow.Follow as Entity
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.follow.Follow as Domain

object FollowMapper {
    fun toEntity(domain: Domain): Entity =
        Entity(
            memberId = domain.memberId,
            followerMemberId = domain.followerMemberId,
        ).apply {
            setId(domain.id)
        }

    fun toDomain(entity: Entity): Domain =
        Domain(
            id = entity.id,
            memberId = entity.memberId,
            followerMemberId = entity.followerMemberId,
            createdAt = entity.createdAt,
        )
}
