package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.constants.MessageQueueConstants
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
    private val deleteTimelineUseCase: DeleteTimelineUseCase,
) {
    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = MessageQueueConstants.POST_WROTE_QUEUE),
                exchange = Exchange(value = MessageQueueConstants.EXCHANGE_DIRECT),
                key = [MessageQueueConstants.POST_WROTE_ROUTING_KEY],
            ),
        ],
    )
    fun consumePostWroteEvent(@Payload postWroteEvent: PostWroteEvent) {
        createTimelineUseCase.createByPost(postId = postWroteEvent.postId, writerId = postWroteEvent.writerId)
    }

    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = MessageQueueConstants.FOLLOW_QUEUE),
                exchange = Exchange(value = MessageQueueConstants.EXCHANGE_DIRECT),
                key = [MessageQueueConstants.FOLLOW_ROUTING_KEY],
            ),
        ],
    )
    fun consumeFollowEvent(@Payload followEvent: FollowEvent) {
        createTimelineUseCase.createByFollow(followerId = followEvent.followerId, followId = followEvent.followId)
    }

    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = MessageQueueConstants.UNFOLLOW_QUEUE),
                exchange = Exchange(value = MessageQueueConstants.EXCHANGE_DIRECT),
                key = [MessageQueueConstants.UNFOLLOW_ROUTING_KEY],
            ),
        ],
    )
    fun consumeUnFollowEvent(@Payload unFollowEvent: UnFollowEvent) {
        deleteTimelineUseCase.deleteByUnFollow(followerId = unFollowEvent.followerId, followId = unFollowEvent.followId)
    }
}
