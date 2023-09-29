package dev.hoon.deepdive.heavytraffic.flitter.application.port.exception

/**
 * 팔로우 실패 예외
 */
class CannotFollowException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_FOLLOW, cause)

/**
 * 언팔로우 실패 예외
 */
class CannotUnFollowException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_UNFOLLOW, cause)
