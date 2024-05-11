@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class MemberDto {
    data class Response(
        val id: UUID,
        val nickname: String,
        val email: String,
        val birthday: LocalDate,
        val createdAt: LocalDateTime,
        var updatedAt: LocalDateTime,
    )
}
