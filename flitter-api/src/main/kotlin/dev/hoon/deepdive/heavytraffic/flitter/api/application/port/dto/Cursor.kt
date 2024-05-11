@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto

import dev.hoon.deepdive.heavytraffic.flitter.core.utils.IdGenerator
import java.util.*

data class CursorRequest(
    val key: UUID = NO_KEY,
    val size: Long = 10,
) {
    companion object {
        val NO_KEY = IdGenerator.min()

        fun of(
            key: UUID?,
            size: Long?,
        ) = CursorRequest(
            key = key ?: NO_KEY,
            size = size ?: 10,
        )
    }

    fun hasKey() = this.key != NO_KEY

    fun next(nextKey: UUID) = CursorRequest(nextKey, this.size)
}

data class CursorResponse<T>(
    val next: CursorRequest? = null,
    val body: List<T>,
)
