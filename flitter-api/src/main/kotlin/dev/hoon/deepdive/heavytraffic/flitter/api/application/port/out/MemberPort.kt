@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.MemberNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import java.util.*

/**
 * 회원 포트
 */
interface MemberPort {
    /**
     * 저장
     *
     * @param member 저장할 회원
     * @return 저장된 회원
     */
    fun create(member: Member): Member

    /**
     * 회원 조회
     *
     * @param id 조회할 회원 아이디
     * @return 아이디에 해당하는 회원
     * @throws MemberNotFoundException 아이디에 해당하는 회원이 존재하지 않는 경우
     */
    @Throws(MemberNotFoundException::class)
    fun get(id: UUID): Member

    /**
     * 회원 목록 조회
     *
     * @param ids 조회할 회원 아이디 목록
     * @return 아이디에 해당하는 회원 목록
     */
    fun get(ids: List<UUID>): List<Member>

    /**
     * 닉네임으로 회원 조회
     *
     * @param nickname 조회할 닉네임
     * @return 닉네임에 해당하는 회원
     */
    fun getByNickname(nickname: String): Member?

    /**
     * 이메일로 회원 조회
     *
     * @param email 조회할 이메일
     * @return 이메일에 해당하는 회원
     */
    fun getByEmail(email: String): Member?

    /**
     * 삭제
     *
     * @param id 삭제할 회원 아이디
     */
    fun delete(id: UUID)
}
