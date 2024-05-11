package dev.hoon.deepdive.heavytraffic.flitter.core.exception

/**
 * Flitter 상위 예외
 */
open class FlitterException : RuntimeException {
    private val flitterErrorCode: FlitterErrorCode

    constructor(flitterErrorCode: FlitterErrorCode) : super(flitterErrorCode.message) {
        this.flitterErrorCode = flitterErrorCode
    }
    constructor(flitterErrorCode: FlitterErrorCode, message: String?) : super(message) {
        this.flitterErrorCode = flitterErrorCode
    }
    constructor(flitterErrorCode: FlitterErrorCode, cause: Throwable?) : super(flitterErrorCode.message, cause) {
        this.flitterErrorCode = flitterErrorCode
    }
    constructor(flitterErrorCode: FlitterErrorCode, message: String?, cause: Throwable?) : super(message, cause) {
        this.flitterErrorCode = flitterErrorCode
    }

    fun getErrorCode() = this.flitterErrorCode.code

    fun getErrorMessage() = message
}
