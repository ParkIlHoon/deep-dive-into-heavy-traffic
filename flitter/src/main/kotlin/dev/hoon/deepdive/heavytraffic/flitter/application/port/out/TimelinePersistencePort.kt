package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline

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
    fun findAllByMemberId(memberId: Long, cursorRequest: CursorRequest): CursorResponse<Timeline>
}