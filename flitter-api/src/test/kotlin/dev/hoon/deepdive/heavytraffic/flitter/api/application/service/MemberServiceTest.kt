package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.MemberDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotJoinException
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.member.Member
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import java.lang.RuntimeException
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class MemberServiceTest {
    @Mock
    private lateinit var memberQueuePort: MessageQueuePort

    @Test
    fun `join 유효성체크 닉네임`() {
        // given
        val mockMember = Member(
            nickname = "nickname",
            email = "foo@flitter.hoon.dev",
            birthday = LocalDate.now()
        )
        val mockRequest = MemberDto.JoinRequest(
            nickname = mockMember.nickname,
            email = mockMember.email,
            birthday = mockMember.birthday
        )

        val memberPort = mock<MemberPort> {
            on { getByNickname(anyString()) } doReturn mockMember
        }
        val memberService = MemberService(memberPort, memberQueuePort)

        // when, then
        assertThrows(CannotJoinException::class.java) {
            memberService.join(mockRequest)
        }
    }

    @Test
    fun `join 유효성체크 이메일`() {
        // given
        val mockMember = Member(
            nickname = "nickname",
            email = "foo@flitter.hoon.dev",
            birthday = LocalDate.now()
        )
        val mockRequest = MemberDto.JoinRequest(
            nickname = mockMember.nickname,
            email = mockMember.email,
            birthday = mockMember.birthday
        )

        val memberPort = mock<MemberPort> {
            on { getByNickname(anyString()) } doReturn null
            on { getByEmail(anyString()) } doReturn mockMember
        }
        val memberService = MemberService(memberPort, memberQueuePort)

        // when, then
        assertThrows(CannotJoinException::class.java) {
            memberService.join(mockRequest)
        }
    }

    @Test
    fun `join Member 생성 중 예외 발생 시 CannotJoinException 던짐`() {
        // given
        val mockMember = Member(
            nickname = "nickname",
            email = "foo@flitter.hoon.dev",
            birthday = LocalDate.now()
        )
        val mockRequest = MemberDto.JoinRequest(
            nickname = mockMember.nickname,
            email = mockMember.email,
            birthday = mockMember.birthday
        )

        val memberPort = mock<MemberPort> {
            on { getByNickname(anyString()) } doReturn null
            on { getByEmail(anyString()) } doReturn null
            on { create(anyVararg(Member::class)) } doThrow RuntimeException::class
        }
        val memberService = MemberService(memberPort, memberQueuePort)

        // when, then
        assertThrows(CannotJoinException::class.java) {
            memberService.join(mockRequest)
        }
    }

    @Test
    fun `join 성공`() {
        // given
        val mockMember = Member(
            nickname = "nickname",
            email = "foo@flitter.hoon.dev",
            birthday = LocalDate.now()
        )
        val mockRequest = MemberDto.JoinRequest(
            nickname = mockMember.nickname,
            email = mockMember.email,
            birthday = mockMember.birthday
        )

        val memberPort = mock<MemberPort> {
            on { getByNickname(anyString()) } doReturn null
            on { getByEmail(anyString()) } doReturn null
            on { create(anyVararg(Member::class)) } doReturn mockMember
        }
        val memberService = MemberService(memberPort, memberQueuePort)

        // when
        val response = memberService.join(mockRequest)

        // then
        assertEquals(mockMember.id, response.id)
        assertEquals(mockMember.nickname, response.nickname)
        assertEquals(mockMember.email, response.email)
        assertEquals(mockMember.birthday, response.birthday)
        assertEquals(mockMember.createdAt, response.createdAt)
        assertEquals(mockMember.updatedAt, response.updatedAt)
    }
}