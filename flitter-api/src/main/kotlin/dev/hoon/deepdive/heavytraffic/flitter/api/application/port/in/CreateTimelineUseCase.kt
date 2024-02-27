package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.TimelineDto

/**
 * 타입라인 생성 유스 케이스
 */
fun interface CreateTimelineUseCase {
    /**
     * 타임라인 생성
     *
     * @param createRequests 타임라인 생성 객체 목록
     */
    fun create(createRequests: List<TimelineDto.CreateRequest>)
}