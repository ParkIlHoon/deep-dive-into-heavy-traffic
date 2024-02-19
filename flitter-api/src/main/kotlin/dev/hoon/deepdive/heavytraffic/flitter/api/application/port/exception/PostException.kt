package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception

/**
 * 포스트 찾을 수 없음 예외
 */
class PostNotFoundException(message: String) : FlitterException(ErrorCode.POST_NOT_FOUND, message)

/**
 * 포스트 작성 실패 예외
 */
class CannotWritePostException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_WRITE_POST, cause)

/**
 * 포스트 좋아요 실패 예외
 */
class CannotLikePostException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_LIKE_POST, cause)

/**
 * 포스트 좋아요 취소 실패 예외
 */
class CannotUnLikePostException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_UNLIKE_POST, cause)

/**
 * 포스트 삭제 실패 예외
 */
class CannotDeletePostException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_WRITE_POST, cause)