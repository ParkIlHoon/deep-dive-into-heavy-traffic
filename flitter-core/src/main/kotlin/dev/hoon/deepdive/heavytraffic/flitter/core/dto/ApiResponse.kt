package dev.hoon.deepdive.heavytraffic.flitter.core.dto

import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException

data class ApiResponse<T>(
    val header: Header,
    val body: T?,
) {
    data class Header(
        val resultCode: Int,
        val resultMessage: String?,
        val successful: Boolean,
    ) {
        companion object {
            fun success() =
                Header(
                    resultCode = 0,
                    resultMessage = "ok",
                    successful = true,
                )

            fun fail(exception: FlitterException) =
                Header(
                    resultCode = exception.getErrorCode(),
                    resultMessage = exception.getErrorMessage(),
                    successful = false,
                )

            fun fail(
                resultCode: Int,
                resultMessage: String,
            ) = Header(
                resultCode = resultCode,
                resultMessage = resultMessage,
                successful = false,
            )
        }
    }

    companion object {
        fun <T> success(body: T): ApiResponse<T> =
            ApiResponse(
                header = Header.success(),
                body = body,
            )

        fun fail(exception: FlitterException): ApiResponse<Unit> =
            ApiResponse(
                header = Header.fail(exception),
                body = null,
            )

        fun fail(resultMessage: String): ApiResponse<Unit> =
            ApiResponse(
                header = Header.fail(-1, resultMessage),
                body = null,
            )
    }
}
