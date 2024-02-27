package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.dto.PostDto
import java.util.UUID

/**
 * 포스트 조회 유스 케이스
 */
fun interface ReadPostUseCase {
    /**
     * 특정 작성자의 모든 포스트 조회
     *
     * @param writerId 작성자 회원 아이디
     * @return 작성자에 해당하는 모든 포스트 목록
     */
    fun readAllByWriter(writerId: UUID): List<PostDto.Response>
}