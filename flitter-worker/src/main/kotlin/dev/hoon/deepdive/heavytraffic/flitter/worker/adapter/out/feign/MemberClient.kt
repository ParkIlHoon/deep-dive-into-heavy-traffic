package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign

import dev.hoon.deepdive.heavytraffic.flitter.core.dto.ApiResponse
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.MemberDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

/**
 * 회원 API FeignClient
 */
@FeignClient(name = "member-api", url = "\${application.feign.member-api.url}")
interface MemberClient {
    /**
     * 회원 조회
     * @param memberId 조회할 회원 아이디
     * @return 회원 아이디에 해당하는 회원 정보가 담긴 API 응답 객체
     */
    @GetMapping("/api/v1.0/members/{memberId}")
    fun getMember(@PathVariable("memberId") memberId: UUID): ApiResponse<MemberDto.Response>
}