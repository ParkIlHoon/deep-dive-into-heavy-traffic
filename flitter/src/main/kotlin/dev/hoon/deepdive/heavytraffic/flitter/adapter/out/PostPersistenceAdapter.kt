package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.mapper.PostMapper
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.PostLikeRepository
import dev.hoon.deepdive.heavytraffic.flitter.adapter.out.repository.PostRepository
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostLikePersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class PostPersistenceAdapter(
    private val postRepository: PostRepository,
    private val postLikeRepository: PostLikeRepository
): PostPersistencePort, PostLikePersistencePort {
    @Transactional
    override fun save(postLike: PostLike): PostLike =
        PostMapper.toEntity(postLike)
            .let {
                postLikeRepository.save(it)
            }.let {
                PostMapper.toDomain(it)
            }

    @Transactional
    override fun delete(postLike: PostLike) =
        PostMapper.toEntity(postLike)
            .let {
                postLikeRepository.delete(it)
            }

    override fun count(postId: UUID): Long = postLikeRepository.countByPostId(postId)

    @Transactional
    override fun save(post: Post): Post =
        PostMapper.toEntity(post)
            .let {
                postRepository.save(it)
            }.let {
                PostMapper.toDomain(it)
            }

    override fun findById(id: UUID): Post =
        postRepository.findById(id)
            .let {
                PostMapper.toDomain(it)
            }

    override fun findAllByIdIn(ids: List<UUID>): List<Post> =
        postRepository.findAllByIdIn(ids)
            .map {
                PostMapper.toDomain(it)
            }

    override fun findAllByMemberId(memberId: UUID): List<Post> =
        postRepository.findAllByWriterId(memberId)
            .map {
                PostMapper.toDomain(it)
            }
}