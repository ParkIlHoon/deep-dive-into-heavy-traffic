package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception

import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterErrorCode
import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException

// ktlint-disable filename

/**
 * 타임라인 조회 실패 예외
 */
class CannotReadTimelineException(cause: Throwable) : FlitterException(FlitterErrorCode.TIMELINE_NOT_FOUND, cause)

/**
 * 타임라인 생성 실패 예외
 */
class CannotCreateTimelineException(cause: Throwable) : FlitterException(FlitterErrorCode.CANNOT_CREATE_TIMELINE, cause)

/**
 * 타임라인 삭제 실패 예외
 */
class CannotDeleteTimelineException(cause: Throwable) : FlitterException(FlitterErrorCode.CANNOT_DELETE_TIMELINE, cause)
