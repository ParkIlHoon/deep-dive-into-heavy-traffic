package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberChangeNicknameUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberJoinUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.MemberLeaveUseCase
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.ReadMemberUseCase
import dev.hoon.deepdive.heavytraffic.flitter.core.utils.IdGenerator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class MemberControllerTest {

    @Mock
    private lateinit var memberLeaveUseCase: MemberLeaveUseCase
    @Mock
    private lateinit var readMemberUseCase: ReadMemberUseCase
    @Mock
    private lateinit var memberChangeNicknameUseCase: MemberChangeNicknameUseCase

    @Test
    fun `join success`() {
        // given
        val mockRequest = MemberDto.JoinRequest(
            nickname = "nickname",
            email = "foo@flitter.hoon.dev",
            birthday = LocalDate.now()
        )
        val mockResponse = MemberDto.Response(
            id = IdGenerator.generate(),
            nickname = mockRequest.nickname,
            email = mockRequest.email,
            birthday = mockRequest.birthday,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val memberJoinUseCase = mock<MemberJoinUseCase>() {
            on { join(anyVararg(MemberDto.JoinRequest::class)) } doReturn mockResponse
        }
        val memberController = MemberController(memberJoinUseCase, readMemberUseCase, memberChangeNicknameUseCase, memberLeaveUseCase)

        // when
        val apiResponse = memberController.join(mockRequest)

        // then
        assertTrue(apiResponse.header.successful)
        assertEquals(mockRequest.nickname, apiResponse.body?.nickname)
        assertEquals(mockRequest.email, apiResponse.body?.email)
        assertEquals(mockRequest.birthday, apiResponse.body?.birthday)
        assertEquals(mockResponse.id, apiResponse.body?.id)
        assertEquals(mockResponse.createdAt, apiResponse.body?.createdAt)
        assertEquals(mockResponse.updatedAt, apiResponse.body?.updatedAt)
    }
}