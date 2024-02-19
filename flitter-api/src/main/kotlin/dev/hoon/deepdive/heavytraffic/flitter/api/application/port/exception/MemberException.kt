package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception // ktlint-disable filename

/**
 * 회원 찾을 수 없음 예외
 */
class MemberNotFoundException(message: String) : FlitterException(ErrorCode.MEMBER_NOT_FOUND, message)

/**
 * 회원 가입 실패 예외
 */
class CannotJoinException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_JOIN, cause)

/**
 * 회원 탈퇴 실패 예외
 */
class CannotLeaveException(cause: Throwable) : FlitterException(ErrorCode.CANNOT_LEAVE, cause)
