package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`

interface LikePostUseCase {

    fun like(memberId: Long, postId: Long)

    fun unLike(memberId: Long, postId: Long)

}