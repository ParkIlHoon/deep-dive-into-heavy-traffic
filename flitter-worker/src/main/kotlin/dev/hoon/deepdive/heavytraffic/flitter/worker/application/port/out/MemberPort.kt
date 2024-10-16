@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.MemberNotFoundException
import java.util.*
import kotlin.jvm.Throws

/**
 * 회원 포트
 */
fun interface MemberPort {
    /**
     * 회원 조회
     *
     * @param memberId 조회할 회원 아이디
     * @return 아이디에 해당하는 회원
     */
    @Throws(MemberNotFoundException::class)
    fun get(memberId: UUID): MemberDto.Response
}
