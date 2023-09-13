package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto

import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import java.time.LocalDateTime

class TimelineDto {
    data class Response(
        val id: Long,
        val postId: Long,
        val writerId: Long,
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
                    writerId = post.memberId,
                    writerNickname = writer.nickname,
                    contents = post.contents,
                    like = post.like,
                    createdAt = post.createdAt
                )
        }
    }
}
