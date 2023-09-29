package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper.MemberMapper
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.MemberRepository
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.NicknameHistoryRepository
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MemberPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.NicknameHistoryPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.NicknameHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val nicknameHistoryRepository: NicknameHistoryRepository,
) : MemberPersistencePort, NicknameHistoryPersistencePort {
    @Transactional
    override fun save(member: Member): Member =
        MemberMapper.toEntity(member)
            .let { memberRepository.save(it) }
            .let { MemberMapper.toDomain(it) }

    override fun findById(id: UUID): Member =
        memberRepository.findById(id)
            .let { MemberMapper.toDomain(it) }

    override fun findAllByIdIn(ids: List<UUID>): List<Member> =
        memberRepository.findAllByIdIn(ids)
            .map { MemberMapper.toDomain(it) }

    @Transactional
    override fun save(nicknameHistory: NicknameHistory): NicknameHistory =
        MemberMapper.toEntity(nicknameHistory)
            .let { nicknameHistoryRepository.save(it) }
            .let { MemberMapper.toDomain(it) }

    override fun findAllByMemberId(memberId: UUID): List<NicknameHistory> =
        memberRepository.findById(memberId).nicknameHistories
            .map { MemberMapper.toDomain(it) }
}
