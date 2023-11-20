package dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "API 공통 응답 객체")
data class ApiResponse<T>(
    @Schema(title = "헤더")
    val header: Header,
    @Schema(title = "바디")
    val body: T?,
) {
    @Schema(name = "헤더")
    data class Header(
        @Schema(title = "결과 코드")
        val resultCode: Int,
        @Schema(title = "결과 메시지")
        val resultMessage: String,
        @Schema(title = "성공 여부")
        val successful: Boolean,
    ) {
        companion object {
            fun success() = Header(
                resultCode = 0,
                resultMessage = "ok",
                successful = true,
            )
        }
    }

    companion object {
        fun <T> success(body: T): ApiResponse<T> =
            ApiResponse(
                header = Header.success(),
                body = body,
            )
    }
}
