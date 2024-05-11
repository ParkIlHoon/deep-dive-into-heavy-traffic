@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.CannotDeletePostException
import java.util.*

/**
 * 포스트 삭제 유스 케이스
 */
fun interface DeletePostUseCase {
    @Throws(CannotDeletePostException::class)
    fun delete(postId: UUID)
}
