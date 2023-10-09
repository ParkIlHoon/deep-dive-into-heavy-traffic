package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.FollowingUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.UnFollowingUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/follow")
class FollowController(
    private val followingUseCase: FollowingUseCase,
    private val unFollowingUseCase: UnFollowingUseCase
) {
    @PostMapping("/{from}/{to}")
    fun follow(@PathVariable("from") followerId: UUID, @PathVariable("to") followId: UUID) =
        ApiResponse.success(followingUseCase.follow(followerId, followId))

    @DeleteMapping("/{from}/{to}")
    fun unfollow(@PathVariable("from") followerId: UUID, @PathVariable("to") followId: UUID) =
        ApiResponse.success(unFollowingUseCase.unFollow(followerId, followId))
}