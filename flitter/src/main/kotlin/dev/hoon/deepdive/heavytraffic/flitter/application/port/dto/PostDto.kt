package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto

import java.util.*


class PostDto {
    data class Request(
        val memberId: UUID,
        var contents: String,
    )
}
