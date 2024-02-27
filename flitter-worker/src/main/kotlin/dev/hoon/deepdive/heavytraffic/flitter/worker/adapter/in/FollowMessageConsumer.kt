package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.core.constants.MessageQueueConstants
import dev.hoon.deepdive.heavytraffic.flitter.core.event.FollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.FollowUseCase
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

/**
 * 팔로우 메시지 컨슈머
 */
@Service
class FollowMessageConsumer(
    private val followUseCase: FollowUseCase,
) {
    /**
     * 팔로우 이벤트 구독
     */
    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = MessageQueueConstants.FOLLOW_QUEUE),
                exchange = Exchange(value = MessageQueueConstants.EXCHANGE_DIRECT),
                key = [MessageQueueConstants.FOLLOW_ROUTING_KEY],
            ),
        ],
    )
    fun consumeFollowEvent(@Payload followEvent: FollowEvent) =
        followUseCase.executeFollowAfterTask(followerMemberId = followEvent.followerMemberId, memberId = followEvent.memberId)
}