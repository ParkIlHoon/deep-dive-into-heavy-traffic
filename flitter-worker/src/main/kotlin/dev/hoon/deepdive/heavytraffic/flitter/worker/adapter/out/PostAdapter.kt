@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

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
) : PostPort, PostLikePort {
    override fun get(id: UUID): PostDto.Response {
        val apiResponse = postClient.getPost(id)
        if (!apiResponse.header.successful) {
            throw FlitterApiException(apiResponse.header.resultCode, apiResponse.header.resultMessage)
        }
        return apiResponse.body ?: throw PostNotFoundException("포스트 API로부터 포스트 정보를 불러오지 못했습니다.")
    }

    override fun getByWriter(memberId: UUID): List<PostDto.Response> {
        val apiResponse = postClient.getPostByWriter(memberId)
        if (!apiResponse.header.successful) {
            throw FlitterApiException(apiResponse.header.resultCode, apiResponse.header.resultMessage)
        }
        return apiResponse.body ?: throw PostNotFoundException("포스트 API로부터 포스트 정보를 불러오지 못했습니다.")
    }

    @Transactional
    override fun deleteByWriter(writerId: UUID) {
        postClient.deletePostsByWriter(writerId)
    }

    @Transactional
    override fun deleteAllByMemberId(memberId: UUID) {
        postClient.unlikePostsByMember(memberId)
    }
}
