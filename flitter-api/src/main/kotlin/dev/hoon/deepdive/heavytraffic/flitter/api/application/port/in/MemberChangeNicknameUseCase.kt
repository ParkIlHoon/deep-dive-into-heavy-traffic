@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotChangeNicknameException
import java.util.UUID

/**
 * 회원 닉네임 변경 유스 케이스
 */
fun interface MemberChangeNicknameUseCase {
    /**
     * 회원 닉네임 변경
     *
     * @param id 변경할 회원 아이디
     * @param newNickname 변경할 닉네임
     * @throws CannotChangeNicknameException 닉네임 변경 실패시
     */
    @Throws(CannotChangeNicknameException::class)
    fun changeNickname(
        id: UUID,
        newNickname: String,
    ): MemberDto.Response
}
