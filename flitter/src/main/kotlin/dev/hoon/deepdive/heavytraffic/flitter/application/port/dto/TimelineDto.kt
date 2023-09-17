package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto

import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import java.time.LocalDateTime
import java.util.UUID

class TimelineDto {
    data class Response(
        val id: UUID,
        val postId: UUID,
        val writerId: UUID,
        val writerNickname: String,
        var contents: String,
        var like: Long = 0L,
        val createdAt: LocalDateTime
    ) {
        companion object {
            fun of(timeline: Timeline, post:Post, writer: Member) =
                TimelineDto.Response(
                    id = timeline.id!!,
                    postId = timeline.postId,
                    writerId = post.writerId,
                    writerNickname = writer.nickname,
                    contents = post.contents,
                    like = post.like,
                    createdAt = post.createdAt
                )
        }
    }
}
