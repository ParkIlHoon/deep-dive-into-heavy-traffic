package dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.timeline.Timeline as TimelineEntity
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline as TimelineDomain

object TimelineMapper {

    fun toEntity(domain: TimelineDomain): TimelineEntity =
        TimelineEntity(
            memberId = domain.memberId,
            postId = domain.postId,
            postedAt = domain.postedAt,
        ).apply {
            setId(domain.id)
        }

    fun toDomain(entity: TimelineEntity): TimelineDomain =
        TimelineDomain(
            id = entity.id,
            memberId = entity.memberId,
            postId = entity.postId,
            postedAt = entity.postedAt,
            createdAt = entity.createdAt,
        )
}
