package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotLeaveException
import java.util.*

/**
 * 회원 탈퇴 유스 케이스
 */
fun interface MemberLeaveUseCase {
    /**
     * 회원 탈퇴
     *
     * @throws CannotLeaveException 회원 탈퇴 실패시
     */
    @Throws(CannotLeaveException::class)
    fun leave(memberId: UUID)
}