package dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.member.NicknameHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NicknameHistoryRepository: JpaRepository<NicknameHistory, UUIDPrimaryKey> {
}