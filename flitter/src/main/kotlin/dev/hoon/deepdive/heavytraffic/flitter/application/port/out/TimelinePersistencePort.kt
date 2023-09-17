package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import java.util.*

/**
 * 타임라인 영속성 포트
 */
interface TimelinePersistencePort {
    /**
     * 저장
     *
     * @param timeline 저장할 타임라인
     * @return 저장된 타임라인
     */
    fun save(timeline: Timeline): Timeline
    /**
     * 저장
     *
     * @param timelines 저장할 타임라인 목록
     */
    fun saveAll(timelines: List<Timeline>)
    /**
     * 조회
     *
     * @param memberId 회원 아이디
     * @param cursorRequest 커서
     * @return 타임라인 목록(포스팅 일시 역순정렬)
     */
    fun findAllByMemberId(memberId: UUID, cursorRequest: CursorRequest): CursorResponse<Timeline>
    /**
     * 조회
     *
     * @param memberId 회원 아이디
     * @param postIds 포스트 아이디 목록
     * @return 회원 아이디와 포스트 아이디에 해당하는 타임라인 목록
     */
    fun findAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>): List<Timeline>
    /**
     * 삭제
     *
     * @param memberId 회원 아이디
     * @param postIds 포스트 아이디 목록
     */
    fun deleteAllByMemberIdAndPostIdIn(memberId: UUID, postIds: List<UUID>)
}