package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.PostLikeRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.PostRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.PostNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class PostAdapter(
    private val postRepository: PostRepository,
    private val postLikeRepository: PostLikeRepository,
) : PostPort, PostLikePort {
    @Transactional
    override fun create(postLike: PostLike): PostLike = postLikeRepository.save(postLike)

    @Transactional
    override fun delete(postLike: PostLike) = postLikeRepository.delete(postLike)

    override fun count(postId: UUID): Long = postLikeRepository.countByPostId(postId)

    @Transactional
    override fun create(post: Post): Post = postRepository.save(post)

    override fun get(id: UUID): Post =
        postRepository.findById(id) ?: throw PostNotFoundException("존재하지 않는 포스트입니다. id = $id")

    override fun get(ids: List<UUID>): List<Post> =
        postRepository.findAllByIdIn(ids)

    override fun getByWriter(memberId: UUID): List<Post> =
        postRepository.findAllByWriterId(memberId)

    @Transactional
    override fun delete(id: UUID) = postRepository.deleteById(id)

    @Transactional
    override fun deleteByWriter(writerId: UUID) = postRepository.deleteAllByWriterId(writerId)

    @Transactional
    override fun deleteByMemberId(memberId: UUID) = postLikeRepository.deleteAllByMemberId(memberId)
}
