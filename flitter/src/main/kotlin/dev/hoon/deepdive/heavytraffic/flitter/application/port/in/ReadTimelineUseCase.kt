package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotReadTimelineException
import java.util.*

/**
 * 타임라인 조회 유스 케이스
 */
interface ReadTimelineUseCase {
    /**
     * 조회
     *
     * @param memberId 회원 아이디
     * @param cursor 커서
     * @return 타임라인 데이터와 다음 커서 정보
     * @throws CannotReadTimelineException 타임라인 조회 실패 예외
     */
    @Throws(CannotReadTimelineException::class)
    fun read(memberId: UUID, cursor: CursorRequest): CursorResponse<TimelineDto.Response>
}
