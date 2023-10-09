package dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto

data class ApiResponse<T>(
    val header: Header,
    val body: T?
) {
    data class Header(
        val resultCode: Int,
        val resultMessage: String,
        val successful: Boolean,
    ) {
        companion object {
            fun success() = Header(
                resultCode = 0,
                resultMessage = "ok",
                successful = true
            )
        }
    }


    companion object {
        fun <T> success(body: T): ApiResponse<T> =
            ApiResponse(
                header = Header.success(),
                body = body
            )
    }
}
