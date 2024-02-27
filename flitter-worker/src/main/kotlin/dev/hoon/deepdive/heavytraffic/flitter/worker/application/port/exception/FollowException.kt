package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception

import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterErrorCode
import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException

/**
 * 팔로우 실패 예외
 */
class CannotFollowException : FlitterException {
    constructor(cause: Throwable) : super(FlitterErrorCode.CANNOT_FOLLOW, cause)
    constructor(message: String, cause: Throwable) : super(FlitterErrorCode.CANNOT_FOLLOW, message, cause)
}

/**
 * 언팔로우 실패 예외
 */
class CannotUnFollowException(cause: Throwable) : FlitterException(FlitterErrorCode.CANNOT_UNFOLLOW, cause)
