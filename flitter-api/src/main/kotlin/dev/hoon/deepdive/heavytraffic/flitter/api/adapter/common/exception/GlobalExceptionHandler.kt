package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.common.exception

import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * <h1>전역 예외 핸들러</h1>
 */
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Unit>> {
        val message =
            exception.bindingResult.allErrors.map {
                it.defaultMessage
            }.joinToString()
        return ResponseEntity.ok(ApiResponse.fail(message))
    }

    @ExceptionHandler(FlitterException::class)
    fun handleFlitterException(exception: FlitterException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.ok(ApiResponse.fail(exception))
}
