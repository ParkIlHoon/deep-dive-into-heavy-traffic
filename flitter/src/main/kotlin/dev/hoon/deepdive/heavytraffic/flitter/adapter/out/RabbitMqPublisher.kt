package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.constants.MessageQueueConstants
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.FollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.PostWroteEvent
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.UnFollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MessageQueuePort
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class RabbitMqPublisher(
    private val rabbitTemplate: RabbitTemplate
): MessageQueuePort {
    override fun publishPostWroteEvent(postId: Long, writerId: Long) {
        rabbitTemplate.convertAndSend(MessageQueueConstants.EXCHANGE_DIRECT,MessageQueueConstants.POST_WROTE_ROUTING_KEY, PostWroteEvent(postId, writerId))
    }

    override fun publishFollowEvent(followerId: Long, followId: Long) {
        rabbitTemplate.convertAndSend(MessageQueueConstants.EXCHANGE_DIRECT, MessageQueueConstants.FOLLOW_ROUTING_KEY, FollowEvent(followerId, followId))
    }

    override fun publishUnFollowEvent(followerId: Long, followId: Long) {
        rabbitTemplate.convertAndSend(MessageQueueConstants.EXCHANGE_DIRECT, MessageQueueConstants.UNFOLLOW_ROUTING_KEY, UnFollowEvent(followerId, followId))
    }
}