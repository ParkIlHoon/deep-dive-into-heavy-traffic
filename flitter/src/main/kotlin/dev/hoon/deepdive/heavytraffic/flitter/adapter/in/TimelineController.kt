package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.ReadTimelineUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/timelines")
class TimelineController(
    private val readTimelineUseCase: ReadTimelineUseCase,
) {

    @GetMapping("/{memberId}")
    fun read(
        @PathVariable("memberId") memberId: UUID,
        @RequestParam(value = "last", required = false) last: UUID?,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Long?,
    ) =
        ApiResponse.success(readTimelineUseCase.read(memberId, CursorRequest.of(last, size)))
}