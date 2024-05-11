@file:Suppress("ktlint:standard:no-wildcard-imports", "ktlint:standard:package-name")

package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.`in`

import dev.hoon.deepdive.heavytraffic.flitter.core.constants.MessageQueueConstants
import dev.hoon.deepdive.heavytraffic.flitter.core.event.*
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.*
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
class MessageConsumer(
    private val afterWritePostProcessor: AfterWritePostProcessor,
    private val afterDeletePostProcessor: AfterDeletePostProcessor,
    private val afterMemberLeaveProcessor: AfterMemberLeaveProcessor,
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
    ) {
        afterWritePostProcessor.execute(
            postId = postWroteEvent.postId,
            writerId = postWroteEvent.writerId,
            postedAt = postWroteEvent.postedAt,
        )
    }

    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = MessageQueueConstants.POST_DELETE_QUEUE),
                exchange = Exchange(value = MessageQueueConstants.EXCHANGE_DIRECT),
                key = [MessageQueueConstants.POST_DELETE_ROUTING_KEY],
            ),
        ],
    )
    fun consumePostDeleteEvent(
        @Payload postDeleteEvent: PostDeleteEvent,
    ) {
        afterDeletePostProcessor.execute(postId = postDeleteEvent.postId)
    }

    @RabbitListener(
        bindings = [
            QueueBinding(
                value = Queue(value = MessageQueueConstants.MEMBER_LEAVE_QUEUE),
                exchange = Exchange(value = MessageQueueConstants.EXCHANGE_DIRECT),
                key = [MessageQueueConstants.MEMBER_LEAVE_ROUTING_KEY],
            ),
        ],
    )
    fun consumeMemberLeaveEvent(
        @Payload memberLeaveEvent: MemberLeaveEvent,
    ) {
        afterMemberLeaveProcessor.execute(memberId = memberLeaveEvent.memberId)
    }
}
