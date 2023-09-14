package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.FollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.PostWroteEvent
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.UnFollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.CreateTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.DeleteTimelineUseCase
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class MessageConsumer(
    private val createTimelineUseCase: CreateTimelineUseCase,
    private val deleteTimelineUseCase: DeleteTimelineUseCase
) {
    @RabbitListener(bindings = [
        QueueBinding(
            value = Queue(value = "queue.post-wrote"),
            exchange = Exchange(value = "exchange.direct"),
            key = ["post-wrote"]
        )
    ])
    fun consumePostWroteEvent(@Payload postWroteEvent: PostWroteEvent) {
        createTimelineUseCase.createByPost(postId = postWroteEvent.postId, writerId = postWroteEvent.writerId)
    }

    @RabbitListener(bindings = [
        QueueBinding(
            value = Queue(value = "queue.follow"),
            exchange = Exchange(value = "exchange.direct"),
            key = ["follow"]
        )
    ])
    fun consumeFollowEvent(@Payload followEvent: FollowEvent) {
        createTimelineUseCase.createByFollow(followerId = followEvent.followerId, followId = followEvent.followId)
    }

    @RabbitListener(bindings = [
        QueueBinding(
            value = Queue(value = "queue.unfollow"),
            exchange = Exchange(value = "exchange.direct"),
            key = ["unfollow"]
        )
    ])
    fun consumeUnFollowEvent(@Payload unFollowEvent: UnFollowEvent) {
        deleteTimelineUseCase.deleteByUnFollow(followerId = unFollowEvent.followerId, followId = unFollowEvent.followId)
    }
}