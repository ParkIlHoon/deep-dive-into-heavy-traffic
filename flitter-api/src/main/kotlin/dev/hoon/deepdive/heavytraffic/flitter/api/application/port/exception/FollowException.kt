package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception

import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterErrorCode
import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException

/**
 * 팔로우 실패 예외
 */
class CannotFollowException(cause: Throwable) : FlitterException(FlitterErrorCode.CANNOT_FOLLOW, cause) {
}

/**
 * 언팔로우 실패 예외
 */
class CannotUnFollowException(cause: Throwable) : FlitterException(FlitterErrorCode.CANNOT_UNFOLLOW, cause)
