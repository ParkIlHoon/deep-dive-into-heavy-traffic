package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.MemberJoinUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.MemberLeaveUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "회원")
@RestController
@RequestMapping("/api/v1.0/members")
class MemberController(
    private val memberJoinUseCase: MemberJoinUseCase,
    private val memberLeaveUseCase: MemberLeaveUseCase
) {

    @Operation(summary = "회원 가입")
    @PostMapping
    fun join(@RequestBody joinRequest: MemberDto.JoinRequest) =
        ApiResponse.success(memberJoinUseCase.join(joinRequest))

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/{memberId}")
    fun leave(@PathVariable("memberId") memberId: UUID) =
        ApiResponse.success(memberLeaveUseCase.leave(memberId))
}