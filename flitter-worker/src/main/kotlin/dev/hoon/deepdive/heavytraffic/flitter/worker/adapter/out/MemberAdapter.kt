@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.exception.FlitterApiException
import dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.out.feign.MemberClient
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.MemberNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.MemberPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberAdapter(
    private val memberClient: MemberClient,
) : MemberPort {
    override fun get(memberId: UUID): MemberDto.Response {
        val apiResponse = memberClient.getMember(memberId)
        if (!apiResponse.header.successful) {
            throw FlitterApiException(apiResponse.header.resultCode, apiResponse.header.resultMessage)
        }
        return apiResponse.body ?: throw MemberNotFoundException("회원 API로부터 회원 정보를 불러오지 못했습니다.")
    }
}
