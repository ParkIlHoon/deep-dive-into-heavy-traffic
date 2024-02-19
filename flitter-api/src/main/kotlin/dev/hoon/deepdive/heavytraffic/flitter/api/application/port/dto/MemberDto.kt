package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class MemberDto {
    @Schema(name = "회원가입 요청 DTO")
    data class JoinRequest(
        @Schema(title = "닉네임")
        val nickname: String,
        @Schema(title = "이메일")
        val email: String,
        @Schema(title = "생일")
        val birthday: LocalDate,
    )

    data class Response(
        val id: UUID,
        val nickname: String,
        val email: String,
        val birthday: LocalDate,
        val createdAt: LocalDateTime,
        var updatedAt: LocalDateTime,
    )
}
