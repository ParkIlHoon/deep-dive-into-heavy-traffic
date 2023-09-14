package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.LikePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.WritePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostLikePersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postPersistencePort: PostPersistencePort,
    private val postLikePersistencePort: PostLikePersistencePort,
    private val messageQueuePort: MessageQueuePort
): WritePostUseCase, LikePostUseCase {

    @Transactional
    override fun write(postDto: PostDto.Request) {
        val post = postPersistencePort.save(Post(memberId = postDto.memberId, contents = postDto.contents))
        post.id?.let { messageQueuePort.publishPostWroteEvent(postId = it, writerId = post.memberId) }
    }

    @Transactional
    override fun like(memberId: Long, postId: Long) {
        val post = postPersistencePort.findById(postId)
        postLikePersistencePort.save(PostLike(post = post, memberId = memberId))
    }

    @Transactional
    override fun unLike(memberId: Long, postId: Long) {
        val post = postPersistencePort.findById(postId)
        postLikePersistencePort.delete(PostLike(post = post, memberId = memberId))
    }
}