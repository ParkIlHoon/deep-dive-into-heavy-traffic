package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.MemberJoinUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.MemberLeaveUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberJoinUseCase: MemberJoinUseCase,
    private val memberLeaveUseCase: MemberLeaveUseCase
) {

    @PostMapping
    fun join(@RequestBody joinRequest: MemberDto.JoinRequest) =
        ApiResponse.success(memberJoinUseCase.join(joinRequest))

    @DeleteMapping("/{memberId}")
    fun leave(@PathVariable("memberId") memberId: UUID) =
        ApiResponse.success(memberLeaveUseCase.leave(memberId))
}