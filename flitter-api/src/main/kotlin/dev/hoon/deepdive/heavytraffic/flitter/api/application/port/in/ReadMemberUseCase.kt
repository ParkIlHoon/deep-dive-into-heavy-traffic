@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.MemberNotFoundException
import java.util.UUID

/**
 * 멤버 조회 유스 케이스
 */
fun interface ReadMemberUseCase {
    /**
     * 멤버 조회
     *
     * @param memberId 조회할 회원 아이디
     * @return 아이디에 해당하는 회원 응답 객체
     * @throws MemberNotFoundException 아이디에 해당하는 회원이 존재하지 않는 경우
     */
    @Throws(MemberNotFoundException::class)
    fun read(memberId: UUID): MemberDto.Response
}
