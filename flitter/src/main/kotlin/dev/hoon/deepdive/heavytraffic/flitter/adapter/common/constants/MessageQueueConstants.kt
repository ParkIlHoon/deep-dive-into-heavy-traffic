package dev.hoon.deepdive.heavytraffic.flitter.adapter.common.constants

class MessageQueueConstants {
    companion object {
        const val EXCHANGE_DIRECT = "exchange.direct"

        const val POST_WROTE_QUEUE = "queue.post-wrote"
        const val POST_WROTE_ROUTING_KEY = "post-wrote"

        const val FOLLOW_QUEUE = "queue.follow"
        const val FOLLOW_ROUTING_KEY = "follow"

        const val UNFOLLOW_QUEUE = "queue.unfollow"
        const val UNFOLLOW_ROUTING_KEY = "unfollow"
    }
}
