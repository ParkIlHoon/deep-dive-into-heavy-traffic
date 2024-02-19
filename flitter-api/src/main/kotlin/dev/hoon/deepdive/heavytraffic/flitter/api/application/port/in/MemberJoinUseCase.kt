package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotJoinException

/**
 * 회원 가입 유스 케이스
 */
fun interface MemberJoinUseCase {
    /**
     * 회원 가입
     *
     * @throws CannotJoinException 회원 가입 실패시
     */
    @Throws(CannotJoinException::class)
    fun join(memberJoinRequest: MemberDto.JoinRequest): MemberDto.Response
}
