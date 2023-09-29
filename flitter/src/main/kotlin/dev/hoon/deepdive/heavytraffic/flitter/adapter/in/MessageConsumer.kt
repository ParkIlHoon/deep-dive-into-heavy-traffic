package dev.hoon.deepdive.heavytraffic.flitter.adapter.`in` // ktlint-disable package-name

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.constants.MessageQueueConstants
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.* // ktlint-disable no-wildcard-imports
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.internal.* // ktlint-disable no-wildcard-imports
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
    private val afterFollowProcessor: AfterFollowProcessor,
    private val afterUnFollowProcessor: AfterUnFollowProcessor,
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
    fun consumePostWroteEvent(@Payload postWroteEvent: PostWroteEvent) {
        afterWritePostProcessor.execute(postId = postWroteEvent.postId, writerId = postWroteEvent.writerId, postedAt = postWroteEvent.postedAt)
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
    fun consumePostDeleteEvent(@Payload postDeleteEvent: PostDeleteEvent) {
        afterDeletePostProcessor.execute(postId = postDeleteEvent.postId)
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
        afterFollowProcessor.execute(followerId = followEvent.followerId, followId = followEvent.followId)
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
        afterUnFollowProcessor.execute(followerId = unFollowEvent.followerId, followId = unFollowEvent.followId)
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
    fun consumeMemberLeaveEvent(@Payload memberLeaveEvent: MemberLeaveEvent) {
        afterMemberLeaveProcessor.execute(memberId = memberLeaveEvent.memberId)
    }
}
