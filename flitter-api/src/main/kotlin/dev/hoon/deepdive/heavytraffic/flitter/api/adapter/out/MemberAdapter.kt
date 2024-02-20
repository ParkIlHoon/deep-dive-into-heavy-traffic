package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.MemberRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.NicknameHistoryRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.MemberNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.NicknameHistoryPort
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
    override fun create(member: Member): Member = memberRepository.save(member)

    override fun get(id: UUID): Member =
        memberRepository.findById(id) ?: throw MemberNotFoundException("존재하지 않는 사용자입니다. id = $id")

    override fun get(ids: List<UUID>): List<Member> =
        memberRepository.findAllByIdIn(ids)

    override fun getByNickname(nickname: String): Member? =
        memberRepository.findByNickname(nickname)

    override fun getByEmail(email: String): Member? =
        memberRepository.findByEmail(email)

    @Transactional
    override fun delete(id: UUID) = memberRepository.deleteById(id)

    @Transactional
    override fun save(nicknameHistory: NicknameHistory): NicknameHistory = nicknameHistoryRepository.save(nicknameHistory)

    override fun findAllByMemberId(memberId: UUID): List<NicknameHistory> {
        val member = memberRepository.findById(memberId) ?: throw MemberNotFoundException("존재하지 않는 사용자입니다. id = $memberId")
        return member.nicknameHistories
    }
}
