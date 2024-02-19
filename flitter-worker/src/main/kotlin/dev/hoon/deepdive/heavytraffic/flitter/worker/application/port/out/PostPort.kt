package dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import java.util.*

/**
 * 포스트 포트
 */
interface PostPort {
    /**
     * 조회
     *
     * @param id 조회할 아이디
     * @return 아이디에 해당하는 포스트
     */
    fun get(id: UUID): Post

    /**
     * 조회
     *
     * @param memberId 작성자 회원 아이디
     * @return 작성자가 아이디에 해당하는 포스트 목록
     */
    fun getByWriter(memberId: UUID): List<Post>

    /**
     * 삭제
     *
     * @param writerId 작성자 아이디
     */
    fun deleteByWriter(writerId: UUID)
}
