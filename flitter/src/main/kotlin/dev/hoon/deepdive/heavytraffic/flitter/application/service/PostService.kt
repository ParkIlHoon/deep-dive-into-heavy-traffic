package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotDeletePostException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotLikePostException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotUnLikePostException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotWritePostException
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.DeletePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.LikePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.UnlikePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.WritePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class PostService(
    private val postPort: PostPort,
    private val postLikePort: PostLikePort,
    private val memberPort: MemberPort,
    private val messageQueuePort: MessageQueuePort,
) : WritePostUseCase, DeletePostUseCase, LikePostUseCase, UnlikePostUseCase {

    @Transactional
    override fun write(postDto: PostDto.Request) {
        validateMember(memberId = postDto.memberId) { CannotWritePostException(it) }

        val post = postPort.create(Post(writerId = postDto.memberId, contents = postDto.contents))
        post.id?.let { messageQueuePort.publishPostWroteEvent(postId = it, writerId = post.writerId, postedAt = post.createdAt) }
    }

    @Transactional
    override fun delete(postId: UUID) {
        validatePost(postId = postId) { CannotDeletePostException(it) }

        postPort.delete(postId)
    }

    @Transactional
    override fun like(memberId: UUID, postId: UUID) {
        validateMember(memberId = memberId) { CannotLikePostException(it) }
        validatePost(postId = postId) { CannotLikePostException(it) }

        val post = postPort.get(postId)
        postLikePort.create(PostLike(post = post, memberId = memberId))
    }

    @Transactional
    override fun unLike(memberId: UUID, postId: UUID) {
        validateMember(memberId = memberId) { CannotUnLikePostException(it) }
        validatePost(postId = postId) { CannotLikePostException(it) }

        val post = postPort.get(postId)
        postLikePort.delete(PostLike(post = post, memberId = memberId))
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }

    private fun validatePost(postId: UUID, thrower: (Exception) -> Exception) {
        try {
            postPort.get(postId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}
