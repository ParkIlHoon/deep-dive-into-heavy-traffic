package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.FollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.UnFollowingUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "팔로우")
@RestController
@RequestMapping("/api/v1.0/follow")
class FollowController(
    private val followingUseCase: FollowingUseCase,
    private val unFollowingUseCase: UnFollowingUseCase
) {
    @Operation(summary = "팔로우")
    @PostMapping("/{from}/{to}")
    fun follow(@PathVariable("from") followerId: UUID, @PathVariable("to") followId: UUID) =
        ApiResponse.success(followingUseCase.follow(followerId, followId))

    @Operation(summary = "언팔로우")
    @DeleteMapping("/{from}/{to}")
    fun unfollow(@PathVariable("from") followerId: UUID, @PathVariable("to") followId: UUID) =
        ApiResponse.success(unFollowingUseCase.unFollow(followerId, followId))
}