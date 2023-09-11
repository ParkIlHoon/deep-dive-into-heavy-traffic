package dev.hoon.deepdive.heavytraffic.flitter.application.port.exception

/**
 * 팔로우 실패 예외
 */
class CannotFollowException(): RuntimeException()

/**
 * 언팔로우 실패 예외
 */
class CannotUnFollowException(): RuntimeException()