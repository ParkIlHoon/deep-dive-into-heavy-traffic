package dev.hoon.deepdive.heavytraffic.flitter.domain.post

import java.time.LocalDateTime
import java.util.*

/**
 * 포스트
 *
 * @property id 아이디
 * @property writerId 작성 회원 아이디
 * @property contents 내용
 * @property like 좋아요 수
 * @property createdAt 생성일시
 */
class Post(
    val id: UUID? = null,
    val writerId: UUID,
    var contents: String,
    var like: Long = 0L,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun updateContents(newContents: String) {
        this.contents = newContents
        this.updatedAt = LocalDateTime.now()
    }
}