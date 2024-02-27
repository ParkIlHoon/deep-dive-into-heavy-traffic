package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.PostLikeRepository
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.repository.PostRepository
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.exception.FlitterApiException
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign.PostClient
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.PostNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.PostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class PostAdapter(
    private val postClient: PostClient,
    private val postRepository: PostRepository,
    private val postLikeRepository: PostLikeRepository,
) : PostPort, PostLikePort {
    override fun get(id: UUID): Post =
        postRepository.findById(id) ?: throw PostNotFoundException("존재하지 않는 포스트입니다. id = $id")

    override fun getByWriter(memberId: UUID): List<PostDto.Response> {
        val apiResponse = postClient.getPost(memberId)
        if (!apiResponse.header.successful) {
            throw FlitterApiException(apiResponse.header.resultCode, apiResponse.header.resultMessage)
        }
        return apiResponse.body ?: throw PostNotFoundException("포스트 API로부터 포스트 정보를 불러오지 못했습니다.")
    }

    @Transactional
    override fun deleteByWriter(writerId: UUID) = postRepository.deleteAllByWriterId(writerId)

    @Transactional
    override fun deleteByMemberId(memberId: UUID) = postLikeRepository.deleteAllByMemberId(memberId)
}
