package dev.hoon.deepdive.heavytraffic.flitter.application.port.out

/**
 * 메시지 큐 포트
 */
interface MessageQueuePort {
    /**
     * 포스트 작성 이벤트 발행
     *
     * @param post 작성된 포스트
     */
    fun publishPostWroteEvent(postId: Long, writerId: Long)
    /**
     * 팔로우 이벤트 발행
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 팔로우 대상 회원 아이디
     */
    fun publishFollowEvent(followerId: Long, followId: Long)
    /**
     * 언팔로우 이벤트 발행
     *
     * @param followerId 팔로워 회원 아이디
     * @param followId 언팔로우 대상 회원 아이디
     */
    fun publishUnFollowEvent(followerId: Long, followId: Long)
}