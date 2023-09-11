package dev.hoon.deepdive.heavytraffic.flitter.application.port.exception

/**
 * 포스트 좋아요 실패 예외
 */
class CannotLikePostException(): RuntimeException()
/**
 * 포스트 좋아요 취소 실패 예외
 */
class CannotUnLikePostException(): RuntimeException()
/**
 * 포스트 작성 실패 예외
 */
class CannotWritePostException(): RuntimeException()