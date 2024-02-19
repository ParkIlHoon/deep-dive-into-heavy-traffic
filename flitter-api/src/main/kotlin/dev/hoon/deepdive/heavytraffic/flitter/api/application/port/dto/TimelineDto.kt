package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto

import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.UUID

class TimelineDto {
    @Schema(name = "타임라인 응답 DTO")
    data class Response(
        @Schema(title = "타임라인 아이디")
        val id: UUID,
        @Schema(title = "포스트 아이디")
        val postId: UUID,
        @Schema(title = "작성자 아이디")
        val writerId: UUID,
        @Schema(title = "작성자 닉네임")
        val writerNickname: String,
        @Schema(title = "내용")
        var contents: String,
        @Schema(title = "좋아요 수")
        var like: Long = 0L,
        @Schema(title = "작성일시")
        val createdAt: LocalDateTime,
    ) {
        companion object {
            fun of(timeline: Timeline, post: Post, writer: Member) =
                Response(
                    id = timeline.id,
                    postId = timeline.postId,
                    writerId = post.writerId,
                    writerNickname = writer.nickname,
                    contents = post.contents,
                    like = post.like,
                    createdAt = post.createdAt,
                )
        }
    }
}
