package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.core.constants.MessageQueueConstants
import dev.hoon.deepdive.heavytraffic.flitter.core.event.PostLikeEvent
import dev.hoon.deepdive.heavytraffic.flitter.core.event.PostWroteEvent
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.PostLikedUseCase
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.PostWroteUseCase
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class PostMessageConsumer(
    private val postWroteUseCase: PostWroteUseCase,
    private val postLikedUseCase: PostLikedUseCase,
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
    fun consumePostWroteEvent(
        @Payload postWroteEvent: PostWroteEvent,
    ) = postWroteUseCase.afterPostWrote(
        postId = postWroteEvent.postId,
        writerId = postWroteEvent.writerId,
        postedAt = postWroteEvent.postedAt,
    )

    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = MessageQueueConstants.POST_LIKE_QUEUE),
                exchange = Exchange(value = MessageQueueConstants.EXCHANGE_DIRECT),
                key = [MessageQueueConstants.POST_LIKE_ROUTING_KEY],
            ),
        ],
    )
    fun consumePostLikeEvent(
        @Payload postLikeEvent: PostLikeEvent,
    ) = postLikedUseCase.afterPostLiked(
        postId = postLikeEvent.postId,
        memberId = postLikeEvent.memberId,
        likedAt = postLikeEvent.likedAt,
    )
}
