package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign

import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.TimelineDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@FeignClient(name = "timeline-api", url = "\${application.feign.timeline-api.url}")
interface TimelineClient {
    @PostMapping("/api/v1.0/timelines")
    fun create(@RequestBody createRequests: List<TimelineDto.CreateRequest>): ApiResponse<Unit>
}