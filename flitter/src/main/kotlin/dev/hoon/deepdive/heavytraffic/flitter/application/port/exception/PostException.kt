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
/**
 * 포스트 좋아요 삭제 실패 예외
 */
class CannotDeletePostLikeException(): RuntimeException()
/**
 * 포스트 찾을 수 없음 예외
 */
class PostNotFoundException(): RuntimeException()