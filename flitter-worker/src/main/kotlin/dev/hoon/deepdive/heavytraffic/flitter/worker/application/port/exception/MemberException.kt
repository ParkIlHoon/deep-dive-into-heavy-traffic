@file:Suppress("ktlint:standard:filename")

package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception

import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterErrorCode
import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException

/**
 * 회원 찾을 수 없음 예외
 */
class MemberNotFoundException(message: String) : FlitterException(FlitterErrorCode.MEMBER_NOT_FOUND, message)
