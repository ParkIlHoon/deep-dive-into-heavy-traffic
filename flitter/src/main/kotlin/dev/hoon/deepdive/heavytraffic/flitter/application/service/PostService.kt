package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.LikePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.WritePostUseCase
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
    private val postLikePersistencePort: PostLikePersistencePort
): WritePostUseCase, LikePostUseCase {

    @Transactional
    override fun write(postDto: PostDto.Request) {
        postPersistencePort.save(Post(memberId = postDto.memberId, contents = postDto.contents))
        //TODO 타임라인 생성
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