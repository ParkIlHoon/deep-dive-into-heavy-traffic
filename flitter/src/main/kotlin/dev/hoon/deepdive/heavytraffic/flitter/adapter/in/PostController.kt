package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.DeletePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.LikePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.UnlikePostUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.WritePostUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/posts")
class PostController(
    private val writePostUseCase: WritePostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val likePostUseCase: LikePostUseCase,
    private val unlikePostUseCase: UnlikePostUseCase,
) {
    @PostMapping
    fun write(@RequestBody writeRequest: PostDto.Request) =
        ApiResponse.success(writePostUseCase.write(writeRequest))

    @DeleteMapping("/{postId}")
    fun delete(@PathVariable("postId") postId: UUID) =
        ApiResponse.success(deletePostUseCase.delete(postId))

    @PostMapping("/{postId}/likes/{memberId}")
    fun like(@PathVariable("postId") postId: UUID, @PathVariable("memberId") memberId: UUID) =
        ApiResponse.success(likePostUseCase.like(memberId, postId))

    @DeleteMapping("/{postId}/likes/{memberId}")
    fun unLike(@PathVariable("postId") postId: UUID, @PathVariable("memberId") memberId: UUID) =
        ApiResponse.success(unlikePostUseCase.unLike(memberId, postId))
}