package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

class PostDto {
    @Schema(name = "포스트 작성 요청 DTO")
    data class Request(
        @Schema(title = "작성자 아이디")
        val memberId: UUID,
        @Schema(title = "내용")
        var contents: String,
    )
}
