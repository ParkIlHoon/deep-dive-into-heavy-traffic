package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto

data class CursorRequest(
    val key: Long = NO_KEY,
    val size: Int = 10
) {
    companion object {
        private const val NO_KEY = -1L
    }

    fun hasKey() = this.key != NO_KEY
}

data class CursorResponse<T>(
    val next: CursorRequest? = null,
    val body: List<T>,
)
