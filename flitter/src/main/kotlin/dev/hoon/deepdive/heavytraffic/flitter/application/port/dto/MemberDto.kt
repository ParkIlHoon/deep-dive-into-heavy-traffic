package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class MemberDto {
    data class JoinRequest(
        val nickname: String,
        val email: String,
        val birthday: LocalDate,
    )

    data class Response(
        val id: UUID,
        val nickname: String,
        val email: String,
        val birthday: LocalDate,
        val createdAt: LocalDateTime,
        var updatedAt: LocalDateTime,
    )
}
