package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign

import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * 타임라인 API FeignClient
 */
@FeignClient(name = "timeline-api", url = "\${application.feign.timeline-api.url}")
interface TimelineClient {
    /**
     * 타임라인 생성
     * @param createRequests 생성할 타임라인 정보
     * @return API 응답 객체
     */
    @PostMapping("/api/v1.0/timelines")
    fun create(
        @RequestBody createRequests: List<TimelineDto.CreateRequest>,
    ): ApiResponse<Unit>

    /**
     * 타임라인 삭제
     * @param deleteTargets 삭제할 타임라인 정보
     * @return API 응답 객체
     */
    @DeleteMapping("/api/v1.0/timelines")
    fun delete(
        @RequestBody deleteTargets: List<TimelineDto.DeleteRequest>,
    ): ApiResponse<Unit>
}
