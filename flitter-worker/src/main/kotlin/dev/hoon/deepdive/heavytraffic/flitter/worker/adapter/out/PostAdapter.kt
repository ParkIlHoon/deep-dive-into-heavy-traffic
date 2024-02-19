package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.PostLikeRepository
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.PostRepository
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.PostNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class PostAdapter(
    private val postRepository: PostRepository,
    private val postLikeRepository: PostLikeRepository,
) : PostPort, PostLikePort {
    override fun get(id: UUID): Post =
        postRepository.findById(id) ?: throw PostNotFoundException("존재하지 않는 포스트입니다. id = $id")

    override fun getByWriter(memberId: UUID): List<Post> =
        postRepository.findAllByWriterId(memberId)

    @Transactional
    override fun deleteByWriter(writerId: UUID) = postRepository.deleteAllByWriterId(writerId)

    @Transactional
    override fun deleteByMemberId(memberId: UUID) = postLikeRepository.deleteAllByMemberId(memberId)
}
