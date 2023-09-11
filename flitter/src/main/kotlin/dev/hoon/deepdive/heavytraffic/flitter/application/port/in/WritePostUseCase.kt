package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.PostDto

interface WritePostUseCase {

    fun write(postDto: PostDto.Request)

}