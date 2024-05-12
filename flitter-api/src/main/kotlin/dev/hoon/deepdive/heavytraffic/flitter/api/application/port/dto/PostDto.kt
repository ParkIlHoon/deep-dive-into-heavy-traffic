@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime
import java.util.*

class PostDto {
    @Schema(name = "포스트 작성 요청 DTO")
    data class Request(
        @Schema(title = "작성자 아이디")
        @field:NotBlank(message = "작성자 아이디는 필수값입니다")
        val memberId: UUID,
        @Schema(title = "내용")
        @field:NotBlank(message = "작성 내용은 필수값입니다")
        var contents: String,
    )

    @Schema(name = "포스트 응답 DTO")
    data class Response(
        @Schema(name = "포스트 아이디")
        val id: UUID,
        @Schema(name = "작성자 회원 아이디")
        val writerId: UUID,
        @Schema(name = "내용")
        val contents: String,
        @Schema(name = "좋아요")
        val like: Long,
        @Schema(name = "생성일시")
        val createdAt: LocalDateTime,
        @Schema(name = "수정일시")
        val updatedAt: LocalDateTime,
    )
}
