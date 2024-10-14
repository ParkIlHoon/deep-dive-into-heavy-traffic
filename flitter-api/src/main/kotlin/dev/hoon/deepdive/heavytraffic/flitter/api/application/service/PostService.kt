@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.*
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.*
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
    private val messageQueuePort: MessageQueuePort,
) : ReadPostUseCase, WritePostUseCase, DeletePostUseCase, LikePostUseCase, UnlikePostUseCase {
    override fun readAllByWriter(writerId: UUID): List<PostDto.Response> =
        postPort.getByWriter(writerId)
            .map {
                PostDto.Response(
                    id = it.id,
                    writerId = it.writerId,
                    contents = it.contents,
                    like = it.like,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                )
            }

    @Transactional
    override fun write(postDto: PostDto.Request): PostDto.Response {
        try {
            return postPort.create(Post(writerId = postDto.memberId, contents = postDto.contents))
                .let {
                    messageQueuePort.publishPostWroteEvent(postId = it.id, writerId = it.writerId, postedAt = it.createdAt)
                    it
                }
                .let {
                    PostDto.Response(
                        id = it.id,
                        writerId = it.writerId,
                        contents = it.contents,
                        like = it.like,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt,
                    )
                }
        } catch (e: Exception) {
            throw CannotWritePostException(e)
        }
    }

    @Transactional
    override fun delete(postId: UUID) {
        try {
            postPort.get(id = postId)
                .let { postPort.delete(it.id) }
        } catch (e: Exception) {
            throw CannotDeletePostException(e)
        }
    }

    @Transactional
    override fun deleteAllByWriter(writerId: UUID) {
        postPort.deleteAllByWriter(writerId)
    }

    @Transactional
    override fun like(
        postId: UUID,
        memberId: UUID,
    ) {
        try {
            postPort.get(id = postId)
                .run { PostLike(post = this, memberId = memberId) }
                .let { postLikePort.create(it) }
                .let { messageQueuePort.publishPostLikeEvent(it.post.id, it.memberId, it.createdAt) }
        } catch (e: Exception) {
            throw CannotLikePostException(e)
        }
    }

    @Transactional
    override fun unLike(
        postId: UUID,
        memberId: UUID,
    ) {
        try {
            postPort.get(id = postId)
                .run { PostLike(post = this, memberId = memberId) }
                .let { postLikePort.delete(it) }
        } catch (e: Exception) {
            throw CannotUnLikePostException(e)
        }
    }

    @Transactional
    override fun unLikeAllByMember(memberId: UUID) {
        postLikePort.deleteAllByMember(memberId)
    }
}
