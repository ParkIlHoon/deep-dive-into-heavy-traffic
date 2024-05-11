package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign

import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.PostDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

/**
 * 포스트 API FeignClient
 */
@FeignClient(name = "post-api", url = "\${application.feign.post-api.url}")
@SuppressWarnings("kotlin:S6517")
interface PostClient {
    /**
     * 포스트 목록 조회
     */
    @GetMapping("/api/v1.0/posts")
    fun getPostByWriter(
        @RequestParam("writerId") writerId: UUID,
    ): ApiResponse<List<PostDto.Response>>
}
