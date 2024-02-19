package dev.hoon.deepdive.heavytraffic.flitter.core.constants

class MessageQueueConstants {
    companion object {
        const val EXCHANGE_DIRECT = "exchange.direct"

        const val POST_WROTE_QUEUE = "queue.post-wrote"
        const val POST_WROTE_ROUTING_KEY = "post-wrote"

        const val POST_DELETE_QUEUE = "queue.post-delete"
        const val POST_DELETE_ROUTING_KEY = "post-delete"

        const val FOLLOW_QUEUE = "queue.follow"
        const val FOLLOW_ROUTING_KEY = "follow"

        const val UNFOLLOW_QUEUE = "queue.unfollow"
        const val UNFOLLOW_ROUTING_KEY = "unfollow"

        const val MEMBER_LEAVE_QUEUE = "queue.member-leave"
        const val MEMBER_LEAVE_ROUTING_KEY = "member-leave"
    }
}
