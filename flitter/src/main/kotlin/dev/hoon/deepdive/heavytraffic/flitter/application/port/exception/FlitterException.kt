package dev.hoon.deepdive.heavytraffic.flitter.application.port.exception

/**
 * Flitter 상위 예외
 */
open class FlitterException : RuntimeException {

    private val errorCode: ErrorCode

    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }
    constructor(errorCode: ErrorCode, message: String?) : super(message) {
        this.errorCode = errorCode
    }
    constructor(errorCode: ErrorCode, cause: Throwable?) : super(errorCode.message, cause) {
        this.errorCode = errorCode
    }
    constructor(errorCode: ErrorCode, message: String?, cause: Throwable?) : super(message, cause) {
        this.errorCode = errorCode
    }
}

