package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

import dev.hoon.deepdive.heavytraffic.flitter.application.port.exception.PostNotFoundException
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import java.util.*

/**
 * 포스트 영속성 포트
 */
interface PostPersistencePort {
    /**
     * 저장
     *
     * @param post 저장할 포스트
     * @return 저장된 포스트
     */
    fun save(post: Post): Post
    /**
     * 조회
     *
     * @param id 조회할 아이디
     * @return 아이디에 해당하는 포스트
     * @throws PostNotFoundException 아이디에 해당하는 포스트가 존재하지 않는 경우
     */
    @Throws(PostNotFoundException::class)
    fun findById(id: UUID): Post
    /**
     * 조회
     *
     * @param ids 조회할 아이디 목록
     * @return 아이디에 해당하는 포스트 목록
     */
    fun findAllByIdIn(ids: List<UUID>): List<Post>
    /**
     * 조회
     *
     * @param memberId 작성자 회원 아이디
     * @return 작성자가 아이디에 해당하는 포스트 목록
     */
    fun findAllByMemberId(memberId: UUID): List<Post>
}