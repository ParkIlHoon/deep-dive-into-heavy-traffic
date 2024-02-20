package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotJoinException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotLeaveException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberJoinUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberLeaveUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MessageQueuePort
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
        // 유효성 체크
        memberPort.getByNickname(memberJoinRequest.nickname)?.let { throw CannotJoinException("이미 사용중인 닉네임입니다.") }
        memberPort.getByEmail(memberJoinRequest.email)?.let { throw CannotJoinException("이미 사용중인 이메일입니다.") }

        try {
            val member = with(memberJoinRequest) {
                memberPort.create(
                    Member(
                        nickname = nickname,
                        email = email,
                        birthday = birthday,
                    )
                )
            }
            return MemberDto.Response(
                id = member.id,
                nickname = member.nickname,
                email = member.email,
                birthday = member.birthday,
                createdAt = member.createdAt,
                updatedAt = member.updatedAt,
            )
        } catch (exception: Exception) {
            throw CannotJoinException(exception)
        }
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
