package dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.member.NicknameHistory as NicknameHistoryEntity
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.NicknameHistory as NicknameHistoryDomain
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.entity.member.Member as MemberEntity
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member as MemberDomain

object MemberMapper {

    fun toEntity(domain: MemberDomain): MemberEntity =
        MemberEntity(
            nickname = domain.nickname,
            email = domain.email,
            birthDay = domain.birthday
        ).apply {
            setId(domain.id)
        }

    fun toDomain(entity: MemberEntity): MemberDomain =
        MemberDomain(
            id = entity.id,
            nickname = entity.nickname,
            email = entity.email,
            birthday = entity.birthDay,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    fun toEntity(domain: NicknameHistoryDomain): NicknameHistoryEntity =
        NicknameHistoryEntity(
            member = toEntity(domain.member),
            nickname = domain.nickname
        )

    fun toDomain(entity: NicknameHistoryEntity): NicknameHistoryDomain =
        NicknameHistoryDomain(
            id = entity.id,
            member = toDomain(entity.member),
            nickname = entity.nickname,
            createdAt = entity.createdAt
        )
}