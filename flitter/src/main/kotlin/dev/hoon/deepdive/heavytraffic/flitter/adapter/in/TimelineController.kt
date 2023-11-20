package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.ReadTimelineUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "타임라인")
@RestController
@RequestMapping("/api/v1.0/timelines")
class TimelineController(
    private val readTimelineUseCase: ReadTimelineUseCase,
) {
    @Operation(summary = "조회")
    @GetMapping("/{memberId}")
    fun read(
        @PathVariable("memberId") memberId: UUID,
        @RequestParam(value = "last", required = false) last: UUID?,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Long?,
    ) =
        ApiResponse.success(readTimelineUseCase.read(memberId, CursorRequest.of(last, size)))
}