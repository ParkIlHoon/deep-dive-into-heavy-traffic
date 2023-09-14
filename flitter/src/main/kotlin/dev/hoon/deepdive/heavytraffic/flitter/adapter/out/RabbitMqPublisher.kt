package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

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
        rabbitTemplate.convertAndSend("exchange.direct","post-wrote", PostWroteEvent(postId, writerId))
    }

    override fun publishFollowEvent(followerId: Long, followId: Long) {
        rabbitTemplate.convertAndSend("exchange.direct", "follow", FollowEvent(followerId, followId))
    }

    override fun publishUnFollowEvent(followerId: Long, followId: Long) {
        rabbitTemplate.convertAndSend("exchange.direct", "unfollow", UnFollowEvent(followerId, followId))
    }
}