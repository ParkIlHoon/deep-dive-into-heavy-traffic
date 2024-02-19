package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import java.util.*

/**
 * 회원 포트
 */
interface MemberPort {
    /**
     * 회원 조회
     *
     * @param id 조회할 회원 아이디
     * @return 아이디에 해당하는 회원
     */
    fun get(id: UUID): Member
}
