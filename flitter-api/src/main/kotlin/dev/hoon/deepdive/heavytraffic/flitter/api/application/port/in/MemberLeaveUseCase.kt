@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotLeaveException
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
