package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper.MemberMapper
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.MemberRepository
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.NicknameHistoryRepository
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.MemberNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.NicknameHistoryPort
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.NicknameHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberAdapter(
    private val memberRepository: MemberRepository,
    private val nicknameHistoryRepository: NicknameHistoryRepository,
) : MemberPort, NicknameHistoryPort {
    @Transactional
    override fun create(member: Member): Member =
        MemberMapper.toEntity(member)
            .let { memberRepository.save(it) }
            .let { MemberMapper.toDomain(it) }

    override fun get(id: UUID): Member =
        MemberMapper.toDomain(memberRepository.findById(id) ?: throw MemberNotFoundException("존재하지 않는 사용자입니다. id = $id"))

    override fun get(ids: List<UUID>): List<Member> =
        memberRepository.findAllByIdIn(ids)
            .map { MemberMapper.toDomain(it) }

    @Transactional
    override fun delete(id: UUID) = memberRepository.deleteById(id)

    @Transactional
    override fun save(nicknameHistory: NicknameHistory): NicknameHistory =
        MemberMapper.toEntity(nicknameHistory)
            .let { nicknameHistoryRepository.save(it) }
            .let { MemberMapper.toDomain(it) }

    override fun findAllByMemberId(memberId: UUID): List<NicknameHistory> {
        val member = memberRepository.findById(memberId) ?: throw MemberNotFoundException("존재하지 않는 사용자입니다. id = $memberId")
        return member.nicknameHistories.map { MemberMapper.toDomain(it) }
    }
}
