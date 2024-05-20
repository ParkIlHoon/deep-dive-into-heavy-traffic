@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.exception.PostNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import java.util.*

/**
 * 포스트 포트
 */
interface PostPort {
    /**
     * 저장
     *
     * @param post 저장할 포스트
     * @return 저장된 포스트
     */
    fun create(post: Post): Post

    /**
     * 조회
     *
     * @param id 조회할 아이디
     * @return 아이디에 해당하는 포스트
     * @throws PostNotFoundException 아이디에 해당하는 포스트가 존재하지 않는 경우
     */
    @Throws(PostNotFoundException::class)
    fun get(id: UUID): Post

    /**
     * 조회
     *
     * @param ids 조회할 아이디 목록
     * @return 아이디에 해당하는 포스트 목록
     */
    fun get(ids: List<UUID>): List<Post>

    /**
     * 조회
     *
     * @param writerId 조회할 작성자 회원 아이디
     * @return 작성자 아이디에 해당하는 포스트 목록
     */
    fun getByWriter(writerId: UUID): List<Post>

    /**
     * 삭제
     *
     * @param id 삭제할 포스트 아이디
     */
    fun delete(id: UUID): Long

    /**
     * 작성자 기준 삭제
     *
     * @param writerId 삭제할 포스트 작성자 아이디
     */
    fun deleteAllByWriter(writerId: UUID): Long
}
