package dev.hoon.deepdive.heavytraffic.flitter.application.port.dto


class PostDto {
    data class Request(
        val memberId: Long,
        var contents: String,
    )
}
