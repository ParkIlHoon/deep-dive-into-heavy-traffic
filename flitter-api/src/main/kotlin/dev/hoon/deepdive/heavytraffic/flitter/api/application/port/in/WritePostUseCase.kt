@file:Suppress("ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.PostDto
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotWritePostException

/**
 * 포스트 작성 유스 케이스
 */
fun interface WritePostUseCase {
    /**
     * 작성
     *
     * @param postDto 작성 내용
     * @throws CannotWritePostException 포스트 작성 실패 예외
     */
    @Throws(CannotWritePostException::class)
    fun write(postDto: PostDto.Request): PostDto.Response
}
