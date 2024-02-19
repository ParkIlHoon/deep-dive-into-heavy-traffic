package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception // ktlint-disable filename

/**
 * 타임라인 조회 실패 예외
 */
class CannotReadTimelineException(cause: Throwable) : FlitterException(ErrorCode.TIMELINE_NOT_FOUND, cause)

/**
 * 타임라인 생성 실패 예외
 */
class CannotCreateTimelineException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_CREATE_TIMELINE, cause)

/**
 * 타임라인 삭제 실패 예외
 */
class CannotDeleteTimelineException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_DELETE_TIMELINE, cause)
