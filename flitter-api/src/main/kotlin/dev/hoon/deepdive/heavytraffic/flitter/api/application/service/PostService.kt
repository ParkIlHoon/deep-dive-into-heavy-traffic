package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotDeletePostException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotLikePostException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotUnLikePostException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotWritePostException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.*
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostPort
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
) : ReadPostUseCase, WritePostUseCase, DeletePostUseCase, LikePostUseCase, UnlikePostUseCase {
    override fun readAllByWriter(writerId: UUID): List<PostDto.Response> =
        postPort.getByWriter(writerId)
            .map { PostDto.Response(
                id = it.id,
                writerId = it.writerId,
                contents = it.contents,
                like = it.like,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            ) }

    @Transactional
    override fun write(postDto: PostDto.Request) {
        validateMember(memberId = postDto.memberId) { CannotWritePostException(it) }

        postPort.create(Post(writerId = postDto.memberId, contents = postDto.contents))
            .let { messageQueuePort.publishPostWroteEvent(postId = it.id, writerId = it.writerId, postedAt = it.createdAt) }
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
