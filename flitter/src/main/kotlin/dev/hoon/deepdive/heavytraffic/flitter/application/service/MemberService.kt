package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotLeaveException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.MemberJoinUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.MemberLeaveUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberPort: MemberPort,
    private val messageQueuePort: MessageQueuePort,
) : MemberJoinUseCase, MemberLeaveUseCase {
    @Transactional
    override fun join(memberJoinRequest: MemberDto.JoinRequest): MemberDto.Response {
        val member = with(memberJoinRequest) {
            memberPort.create(
                Member(
                    nickname = nickname,
                    email = email,
                    birthday = birthday,
                ),
            )
        }
        return MemberDto.Response(
            id = member.id!!,
            nickname = member.nickname,
            email = member.email,
            birthday = member.birthday,
            createdAt = member.createdAt,
            updatedAt = member.updatedAt,
        )
    }

    @Transactional
    override fun leave(memberId: UUID) {
        validateMember(memberId) { CannotLeaveException(it) }
        memberPort.delete(memberId)
        messageQueuePort.publishMemberLeaveEvent(memberId)
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}
