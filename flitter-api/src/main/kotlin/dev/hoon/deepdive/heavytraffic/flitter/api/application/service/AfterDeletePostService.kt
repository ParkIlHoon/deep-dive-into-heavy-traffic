package dev.hoon.deepdive.heavytraffic.flitter.api.application.service

import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.`in`.internal.AfterDeletePostProcessor
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.PostLikePort
import dev.hoon.deepdive.heavytraffic.flitter.api.application.port.out.TimelinePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class AfterDeletePostService(
    private val timelinePort: TimelinePort,
    private val postLikePort: PostLikePort,
) : AfterDeletePostProcessor {
    @Transactional
    override fun execute(postId: UUID) {
        // 1. 삭제된 포스트를 참조하는 타임라인 제거
        timelinePort.deleteAllByPostId(postId)
    }
}