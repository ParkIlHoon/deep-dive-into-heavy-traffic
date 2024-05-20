package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign

import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.PostDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    /**
     * 포스트 조회
     */
    @GetMapping("/api/v1.0/posts/{id}")
    fun getPost(
        @PathVariable("id") id: UUID,
    ): ApiResponse<PostDto.Response>

    /**
     * 작성자 기준 포스트 삭제
     */
    @DeleteMapping("/api/v1.0/posts")
    fun deletePostsByWriter(
        @RequestParam("writerId") writerId: UUID,
    ): ApiResponse<Unit>

    /**
     * 멤버 기준 좋아요 삭제
     */
    @DeleteMapping("/api/v1.0/posts/likes")
    fun unlikePostsByMember(
        @RequestParam("memberId") memberId: UUID,
    ): ApiResponse<Unit>
}
