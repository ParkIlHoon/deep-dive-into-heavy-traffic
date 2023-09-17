package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto

import com.github.f4b6a3.ulid.Ulid
import java.util.*

data class CursorRequest(
    val key: UUID = NO_KEY,
    val size: Int = 10
) {
    companion object {
        private val NO_KEY = Ulid.MIN.toUuid()
    }

    fun hasKey() = this.key != NO_KEY
}

data class CursorResponse<T>(
    val next: CursorRequest? = null,
    val body: List<T>,
)
