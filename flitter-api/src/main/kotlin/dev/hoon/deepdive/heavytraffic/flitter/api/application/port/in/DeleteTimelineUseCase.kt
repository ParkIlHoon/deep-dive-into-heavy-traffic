@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.TimelineDto

/**
 * 타임라인 제거 유스 케이스
 */
fun interface DeleteTimelineUseCase {
    /**
     * 타임라인 제거
     *
     * @param deleteTargets 타임라인 삭제 대상 목록
     */
    fun delete(deleteTargets: List<TimelineDto.DeleteRequest>)
}
