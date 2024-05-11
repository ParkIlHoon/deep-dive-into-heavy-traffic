@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
import java.util.*

/**
 * 타임라인 포트
 */
interface TimelinePort {
    /**
     * 저장
     *
     * @param timelines 저장할 타임라인 목록
     */
    fun createTimelines(timelines: List<TimelineDto.CreateRequest>)

    /**
     * 삭제
     *
     * @param postId 포스트 아이디
     */
    fun deleteAllByPostId(postId: UUID)

    /**
     * 삭제
     *
     * @param memberId 회원 아이디
     */
    fun deleteAllByMemberId(memberId: UUID)

    fun deleteAllByPostIdIn(postIds: List<UUID>)

    fun deleteTimelines(deleteTargets: List<TimelineDto.DeleteRequest>)
}
