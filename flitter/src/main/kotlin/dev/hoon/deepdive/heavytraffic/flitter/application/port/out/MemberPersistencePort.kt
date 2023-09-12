package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.MemberNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member

/**
 * 회원 영속성 포트
 */
interface MemberPersistencePort {
    /**
     * 저장
     *
     * @param member 저장할 회원
     * @return 저장된 회원
     */
    fun save(member: Member): Member
    /**
     * 회원 조회
     *
     * @param id 조회할 회원 아이디
     * @return 아이디에 해당하는 회원
     * @throws MemberNotFoundException 아이디에 해당하는 회원이 존재하지 않는 경우
     */
    @Throws(MemberNotFoundException::class)
    fun findById(id: Long): Member
    /**
     * 회원 목록 조회
     *
     * @param ids 조회할 회원 아이디 목록
     * @return 아이디에 해당하는 회원 목록
     */
    fun findAllByIdIn(ids: List<Long>): List<Member>
}