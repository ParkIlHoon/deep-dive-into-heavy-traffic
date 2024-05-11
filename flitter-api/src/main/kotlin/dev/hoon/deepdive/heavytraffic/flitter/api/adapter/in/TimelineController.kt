@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.CreateTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.DeleteTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.ReadTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "타임라인")
@RestController
@RequestMapping("/api/v1.0/timelines")
class TimelineController(
    private val createTimelineUseCase: CreateTimelineUseCase,
    private val readTimelineUseCase: ReadTimelineUseCase,
    private val deleteTimelineUseCase: DeleteTimelineUseCase,
) {
    @Operation(summary = "생성")
    @PostMapping
    fun create(
        @RequestBody createRequests: List<TimelineDto.CreateRequest>,
    ): ApiResponse<Unit> = ApiResponse.success(createTimelineUseCase.create(createRequests))

    @Operation(summary = "조회")
    @GetMapping("/{memberId}")
    fun read(
        @PathVariable("memberId") memberId: UUID,
        @RequestParam(value = "last", required = false) last: UUID?,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Long?,
    ): ApiResponse<CursorResponse<TimelineDto.Response>> =
        ApiResponse.success(readTimelineUseCase.read(memberId, CursorRequest.of(last, size)))

    @Operation(summary = "삭제")
    @DeleteMapping
    fun delete(
        @RequestBody deleteTargets: List<TimelineDto.DeleteRequest>,
    ): ApiResponse<Unit> = ApiResponse.success(deleteTimelineUseCase.delete(deleteTargets))
}
