package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.mapper.PostMapper
import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.PostLikeRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.PostRepository
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.PostNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostPort
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.api.domain.post.PostLike
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
    override fun create(postLike: PostLike): PostLike =
        PostMapper.toEntity(postLike)
            .let { postLikeRepository.save(it) }
            .let { PostMapper.toDomain(it) }

    @Transactional
    override fun delete(postLike: PostLike) =
        PostMapper.toEntity(postLike)
            .let { postLikeRepository.delete(it) }

    override fun count(postId: UUID): Long = postLikeRepository.countByPostId(postId)

    @Transactional
    override fun create(post: Post): Post =
        PostMapper.toEntity(post)
            .let { postRepository.save(it) }
            .let { PostMapper.toDomain(it) }

    override fun get(id: UUID): Post =
        PostMapper.toDomain(postRepository.findById(id) ?: throw PostNotFoundException("존재하지 않는 포스트입니다. id = $id"))

    override fun get(ids: List<UUID>): List<Post> =
        postRepository.findAllByIdIn(ids)
            .map { PostMapper.toDomain(it) }

    override fun getByWriter(memberId: UUID): List<Post> =
        postRepository.findAllByWriterId(memberId)
            .map { PostMapper.toDomain(it) }

    @Transactional
    override fun delete(id: UUID) = postRepository.deleteById(id)

    @Transactional
    override fun deleteByWriter(writerId: UUID) = postRepository.deleteAllByWriterId(writerId)

    @Transactional
    override fun deleteByMemberId(memberId: UUID) = postLikeRepository.deleteAllByMemberId(memberId)
}
