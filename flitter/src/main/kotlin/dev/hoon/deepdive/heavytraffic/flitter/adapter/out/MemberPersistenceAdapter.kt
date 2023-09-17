package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

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
    private val nicknameHistoryRepository: NicknameHistoryRepository
): MemberPersistencePort, NicknameHistoryPersistencePort {
    override fun save(member: Member): Member {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Member {
        TODO("Not yet implemented")
    }

    override fun findAllByIdIn(ids: List<UUID>): List<Member> {
        TODO("Not yet implemented")
    }

    override fun save(nicknameHistory: NicknameHistory): NicknameHistory {
        TODO("Not yet implemented")
    }

    override fun findAllByMemberId(memberId: Long): List<NicknameHistory> {
        TODO("Not yet implemented")
    }
}