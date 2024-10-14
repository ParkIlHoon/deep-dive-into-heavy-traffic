@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.*
import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "포스트")
@RestController
@RequestMapping("/api/v1.0/posts")
class PostController(
    private val readPostUseCase: ReadPostUseCase,
    private val writePostUseCase: WritePostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val likePostUseCase: LikePostUseCase,
    private val unlikePostUseCase: UnlikePostUseCase,
) {
    @Operation(summary = "포스트 조회")
    @GetMapping
    fun get(
        @RequestParam("writerId") writerId: UUID,
    ): ApiResponse<List<PostDto.Response>> = ApiResponse.success(readPostUseCase.readAllByWriter(writerId))

    @Operation(summary = "작성")
    @PostMapping
    fun write(
        @Valid @RequestBody writeRequest: PostDto.Request,
    ): ApiResponse<PostDto.Response> = ApiResponse.success(writePostUseCase.write(writeRequest))

    @Operation(summary = "삭제")
    @DeleteMapping("/{postId}")
    fun delete(
        @PathVariable("postId") postId: UUID,
    ): ApiResponse<Unit> = ApiResponse.success(deletePostUseCase.delete(postId))

    @Operation(summary = "작성자 기준 삭제")
    @DeleteMapping
    fun deleteAllByWriter(
        @Parameter(description = "작성자 아이디") @RequestParam("writerId") writerId: UUID,
    ): ApiResponse<Unit> = ApiResponse.success(deletePostUseCase.deleteAllByWriter(writerId))

    @Operation(summary = "좋아요")
    @PostMapping("/{postId}/likes/{memberId}")
    fun like(
        @PathVariable("postId") postId: UUID,
        @PathVariable("memberId") memberId: UUID,
    ): ApiResponse<Unit> = ApiResponse.success(likePostUseCase.like(postId, memberId))

    @Operation(summary = "좋아요 취소")
    @DeleteMapping("/{postId}/likes/{memberId}")
    fun unLike(
        @PathVariable("postId") postId: UUID,
        @PathVariable("memberId") memberId: UUID,
    ): ApiResponse<Unit> = ApiResponse.success(unlikePostUseCase.unLike(postId, memberId))

    @Operation(summary = "특정 멤버의 전체 좋아요 취소")
    @DeleteMapping("/likes")
    fun unLikeAllByMember(
        @RequestParam("memberId") memberId: UUID,
    ): ApiResponse<Unit> = ApiResponse.success(unlikePostUseCase.unLikeAllByMember(memberId))
}
