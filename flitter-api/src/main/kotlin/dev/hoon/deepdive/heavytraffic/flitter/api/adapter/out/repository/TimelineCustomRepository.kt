package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import java.util.*

interface TimelineCustomRepository {
    /**
     * 조회
     *
     * @param timelineId 타임라인 아이디
     * @param memberId 회원 아이디
     * @param size 조회 건수
     * @return 타임라인 목록(포스팅 일시 역순정렬)
     */
    fun findAllByLessThanIdAndMemberIdAndOrderByIdDesc(timelineId: UUID, memberId: UUID, size: Long): List<Timeline>

    /**
     * 조회
     *
     * @param memberId 회원 아이디
     * @param size 조회 건수
     * @return 타임라인 목록(포스팅 일시 역순정렬)
     */
    fun findAllByMemberIdAndOrderByIdDesc(memberId: UUID, size: Long): List<Timeline>
}
