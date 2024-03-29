package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MemberRepository : JpaRepository<Member, UUIDPrimaryKey> {

    fun findById(id: UUID): Member?

    fun findAllByIdIn(ids: List<UUID>): List<Member>

    fun deleteById(id: UUID)
}
