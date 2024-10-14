package dev.hoon.deepdive.heavytraffic.flitter.worker.application.service

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.CannotLikePostException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.PostLikedUseCase
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
@Transactional(readOnly = true)
class PostLikeService(
    private val postPort: PostPort,
    private val memberPort: MemberPort,
    private val postLikePort: PostLikePort,
) : PostLikedUseCase {
    @Transactional
    override fun afterPostLiked(
        postId: UUID,
        memberId: UUID,
        likedAt: LocalDateTime,
    ) {
        // 1. 포스트 검증
        val post = validatePost(postId) { CannotLikePostException(it) }

        // 2. 좋아요 회원 닉네임
        val nickname = memberPort.get(memberId).nickname

        // TODO 좋아요 알림 발송
    }

    private fun validatePost(
        postId: UUID,
        thrower: (Exception) -> Exception,
    ): PostDto.Response {
        try {
            return postPort.get(postId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}
