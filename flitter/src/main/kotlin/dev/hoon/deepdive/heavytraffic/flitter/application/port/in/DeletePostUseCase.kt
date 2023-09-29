package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.CannotDeletePostException
import java.util.*

/**
 * 포스트 삭제 유스 케이스
 */
fun interface DeletePostUseCase {

    @Throws(CannotDeletePostException::class)
    fun delete(postId: UUID)
}