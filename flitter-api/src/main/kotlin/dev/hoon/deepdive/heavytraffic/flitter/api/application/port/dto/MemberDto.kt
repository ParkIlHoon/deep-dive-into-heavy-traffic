@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class MemberDto {
    @Schema(name = "회원가입 요청 DTO")
    data class JoinRequest(
        @field:NotBlank(message = "닉네임을 입력해주세요")
        @Schema(title = "닉네임")
        val nickname: String,
        @field:NotBlank(message = "이메일을 입력해주세요")
        @field:Email(message = "이메일 형식으로 입력해주세요")
        @Schema(title = "이메일")
        val email: String,
        @field:NotNull(message = "생일을 입력해주세요")
        @field:PastOrPresent(message = "생일은 오늘 날짜 이전까지 입력가능합니다")
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

    @Schema(name = "닉네임 변경 요청 DTO")
    data class ChangeNicknameRequest(
        @field:NotBlank(message = "닉네임을 입력해주세요")
        @Schema(title = "닉네임")
        val nickname: String,
    )
}
