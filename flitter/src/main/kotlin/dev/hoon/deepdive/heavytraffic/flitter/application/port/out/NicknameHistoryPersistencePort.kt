package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.member.NicknameHistory
import java.util.UUID

/**
 * 닉네임 이력 영속성 포트
 */
interface NicknameHistoryPersistencePort {
    /**
     * 저장
     *
     * @param nicknameHistory 저장할 닉네임 이력
     * @return 저장된 이력
     */
    fun save(nicknameHistory: NicknameHistory): NicknameHistory

    /**
     * 조회
     *
     * @param memberId 조회할 회원 아이디
     * @return 회원 아이디에 해당하는 닉네임 이력
     */
    fun findAllByMemberId(memberId: UUID): List<NicknameHistory>
}
