package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception

import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterErrorCode
import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException

// ktlint-disable filename

/**
 * 회원 찾을 수 없음 예외
 */
class MemberNotFoundException(message: String) : FlitterException(FlitterErrorCode.MEMBER_NOT_FOUND, message)

/**
 * 회원 가입 실패 예외
 */
class CannotJoinException: FlitterException {
    constructor(message: String?) : super(FlitterErrorCode.CANNOT_JOIN, message)
    constructor(throwable: Throwable) : super(FlitterErrorCode.CANNOT_JOIN, throwable.message, throwable)
}

/**
 * 회원 탈퇴 실패 예외
 */
class CannotLeaveException(cause: Throwable) : FlitterException(FlitterErrorCode.CANNOT_LEAVE, cause)
