@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberChangeNicknameUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberJoinUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberLeaveUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.ReadMemberUseCase
import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.UUID

@Tag(name = "회원")
@RestController
@RequestMapping("/api/v1.0/members")
class MemberController(
    private val memberJoinUseCase: MemberJoinUseCase,
    private val readMemberUseCase: ReadMemberUseCase,
    private val memberChangeNicknameUseCase: MemberChangeNicknameUseCase,
    private val memberLeaveUseCase: MemberLeaveUseCase,
) {
    @Operation(summary = "회원 가입")
    @PostMapping
    fun join(
        @Valid @RequestBody joinRequest: MemberDto.JoinRequest,
    ): ApiResponse<MemberDto.Response> = ApiResponse.success(memberJoinUseCase.join(joinRequest))

    @Operation(summary = "회원 조회")
    @GetMapping("/{memberId}")
    fun get(
        @PathVariable("memberId") memberId: UUID,
    ): ApiResponse<MemberDto.Response> = ApiResponse.success(readMemberUseCase.read(memberId))

    @Operation(summary = "닉네임 변경")
    @PutMapping("/{memberId}/nickname")
    fun changeNickname(
        @PathVariable("memberId") memberId: UUID,
        @Valid @RequestBody changeNicknameRequest: MemberDto.ChangeNicknameRequest,
    ): ApiResponse<MemberDto.Response> =
        ApiResponse.success(memberChangeNicknameUseCase.changeNickname(memberId, changeNicknameRequest.nickname))

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/{memberId}")
    fun leave(
        @PathVariable("memberId") memberId: UUID,
    ): ApiResponse<Unit> = ApiResponse.success(memberLeaveUseCase.leave(memberId))
}
