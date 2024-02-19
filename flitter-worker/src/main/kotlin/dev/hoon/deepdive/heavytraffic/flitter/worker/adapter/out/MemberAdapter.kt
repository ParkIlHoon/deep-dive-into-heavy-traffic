package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.MemberRepository
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.MemberNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.MemberPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberAdapter(
    private val memberRepository: MemberRepository,
) : MemberPort {
    override fun get(id: UUID): Member =
        memberRepository.findById(id) ?: throw MemberNotFoundException("존재하지 않는 사용자입니다. id = $id")
}
