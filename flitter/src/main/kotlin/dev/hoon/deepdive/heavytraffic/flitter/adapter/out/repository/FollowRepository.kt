package dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.follow.Follow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FollowRepository : JpaRepository<Follow, UUIDPrimaryKey> {

    fun findAllByMemberId(memberId: UUID): List<Follow>

    fun findAllByFollowerMemberId(memberId: UUID): List<Follow>

    fun deleteByFollowerMemberIdAndMemberId(followerId: UUID, followId: UUID)

    fun deleteAllByMemberId(followId: UUID)
}
