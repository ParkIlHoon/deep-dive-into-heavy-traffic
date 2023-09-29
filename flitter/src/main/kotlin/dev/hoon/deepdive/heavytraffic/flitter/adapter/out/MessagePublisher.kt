package dev.hoon.deepdive.heavytraffic.flitter.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.constants.MessageQueueConstants
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.FollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.MemberLeaveEvent
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.PostWroteEvent
import dev.hoon.deepdive.heavytraffic.flitter.adapter.common.dto.UnFollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MessageQueuePort
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class MessagePublisher(
    private val rabbitTemplate: RabbitTemplate,
) : MessageQueuePort {
    override fun publishPostWroteEvent(postId: UUID, writerId: UUID, postedAt: LocalDateTime) {
        rabbitTemplate.convertAndSend(MessageQueueConstants.EXCHANGE_DIRECT, MessageQueueConstants.POST_WROTE_ROUTING_KEY, PostWroteEvent(postId, writerId, postedAt))
    }

    override fun publishFollowEvent(followerId: UUID, followId: UUID) {
        rabbitTemplate.convertAndSend(MessageQueueConstants.EXCHANGE_DIRECT, MessageQueueConstants.FOLLOW_ROUTING_KEY, FollowEvent(followerId, followId))
    }

    override fun publishUnFollowEvent(followerId: UUID, followId: UUID) {
        rabbitTemplate.convertAndSend(MessageQueueConstants.EXCHANGE_DIRECT, MessageQueueConstants.UNFOLLOW_ROUTING_KEY, UnFollowEvent(followerId, followId))
    }

    override fun publishMemberLeaveEvent(memberId: UUID) {
        rabbitTemplate.convertAndSend(MessageQueueConstants.EXCHANGE_DIRECT, MessageQueueConstants.MEMBER_LEAVE_ROUTING_KEY, MemberLeaveEvent(memberId))
    }
}
