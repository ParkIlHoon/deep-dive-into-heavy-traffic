package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

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
    override fun save(postLike: PostLike): PostLike {
        TODO("Not yet implemented")
    }

    override fun delete(postLike: PostLike) {
        TODO("Not yet implemented")
    }

    override fun count(postId: UUID): Long {
        TODO("Not yet implemented")
    }

    override fun save(post: Post): Post {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Post {
        TODO("Not yet implemented")
    }

    override fun findAllByIdIn(ids: List<UUID>): List<Post> {
        TODO("Not yet implemented")
    }

    override fun findAllByMemberId(memberId: UUID): List<Post> {
        TODO("Not yet implemented")
    }
}