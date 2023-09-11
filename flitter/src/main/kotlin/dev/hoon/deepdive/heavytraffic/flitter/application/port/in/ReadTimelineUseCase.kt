package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.TimelineDto

interface ReadTimelineUseCase {

    fun read(memberId: Long, cursor: CursorRequest): CursorResponse<TimelineDto.Response>

}