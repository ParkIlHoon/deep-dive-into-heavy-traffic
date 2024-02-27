package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import java.util.*

/**
 * 타임라인 포트
 */
interface TimelinePort {
    /**
     * 조회
     *
     * @param timelineId 타임라인 아이디
     * @param memberId 회원 아이디
     * @param size 조회 건수
     * @return 타임라인 목록(포스팅 일시 역순정렬)
     */
    fun get(timelineId: UUID, memberId: UUID, size: Long): List<Timeline>

    /**
     * 조회
     *
     * @param memberId 회원 아이디
     * @param size 조회 건수
     * @return 타임라인 목록(포스팅 일시 역순정렬)
     */
    fun get(memberId: UUID, size: Long): List<Timeline>

    fun create(timelines: List<Timeline>)
}
