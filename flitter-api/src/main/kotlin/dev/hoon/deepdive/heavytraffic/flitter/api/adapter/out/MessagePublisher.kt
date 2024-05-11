package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.MessageQueuePort
import dev.hoon.deepdive.heavytraffic.flitter.core.constants.MessageQueueConstants
import dev.hoon.deepdive.heavytraffic.flitter.core.event.FollowEvent
import dev.hoon.deepdive.heavytraffic.flitter.core.event.MemberLeaveEvent
import dev.hoon.deepdive.heavytraffic.flitter.core.event.PostWroteEvent
import dev.hoon.deepdive.heavytraffic.flitter.core.event.UnFollowEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class MessagePublisher(
    private val rabbitTemplate: RabbitTemplate,
) : MessageQueuePort {
    override fun publishPostWroteEvent(
        postId: UUID,
        writerId: UUID,
        postedAt: LocalDateTime,
    ) {
        rabbitTemplate.convertAndSend(
            MessageQueueConstants.EXCHANGE_DIRECT,
            MessageQueueConstants.POST_WROTE_ROUTING_KEY,
            PostWroteEvent(postId, writerId, postedAt),
        )
    }

    override fun publishFollowEvent(
        followerMemberId: UUID,
        memberId: UUID,
    ) {
        rabbitTemplate.convertAndSend(
            MessageQueueConstants.EXCHANGE_DIRECT,
            MessageQueueConstants.FOLLOW_ROUTING_KEY,
            FollowEvent(followerMemberId, memberId),
        )
    }

    override fun publishUnFollowEvent(
        followerMemberId: UUID,
        memberId: UUID,
    ) {
        rabbitTemplate.convertAndSend(
            MessageQueueConstants.EXCHANGE_DIRECT,
            MessageQueueConstants.UNFOLLOW_ROUTING_KEY,
            UnFollowEvent(followerMemberId, memberId),
        )
    }

    override fun publishMemberLeaveEvent(memberId: UUID) {
        rabbitTemplate.convertAndSend(
            MessageQueueConstants.EXCHANGE_DIRECT,
            MessageQueueConstants.MEMBER_LEAVE_ROUTING_KEY,
            MemberLeaveEvent(memberId),
        )
    }
}
