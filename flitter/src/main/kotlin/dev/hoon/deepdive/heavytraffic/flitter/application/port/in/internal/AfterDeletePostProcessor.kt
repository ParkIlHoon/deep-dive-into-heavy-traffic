package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.internal // ktlint-disable package-name

import java.util.UUID

/**
 * 포스트 삭제 후처리
 */
fun interface AfterDeletePostProcessor {
    /**
     * 실행
     *
     * 1. 삭제된 포스트를 참조하는 타임라인 제거
     *
     * @param postId 삭제된 포스트 아이디
     */
    fun execute(postId: UUID)
}