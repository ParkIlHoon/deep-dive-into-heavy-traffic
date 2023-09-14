package dev.hoon.deepdive.heavytraffic.flitter.application.service

import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorRequest
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.CursorResponse
import dev.hoon.deepdive.heavytraffic.flitter.application.port.dto.TimelineDto
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.CreateTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.DeleteTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.ReadTimelineUseCase
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.FollowPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.MemberPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.PostPersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.application.port.out.TimelinePersistencePort
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TimelineService(
    private val timelinePersistencePort: TimelinePersistencePort,
    private val postPersistencePort: PostPersistencePort,
    private val memberPersistencePort: MemberPersistencePort,
    private val followPersistencePort: FollowPersistencePort
): ReadTimelineUseCase, CreateTimelineUseCase, DeleteTimelineUseCase {
    override fun read(memberId: Long, cursor: CursorRequest): CursorResponse<TimelineDto.Response> {
        val timelines = timelinePersistencePort.findAllByMemberId(memberId, cursor)
        val posts = postPersistencePort.findAllByIdIn(timelines.body.map { memberId })
        val writers = memberPersistencePort.findAllByIdIn(posts.map { memberId }).toSet()

        val timelineResp = timelines.body.map {
            val post = posts.first { post -> post.id == it.postId }
            val writer = writers.first { writer -> writer.id == post.memberId }

            TimelineDto.Response.of(it, post, writer)
        }
        return CursorResponse(timelines.next, timelineResp)
    }

    @Transactional
    override fun createByPost(postId: Long, writerId: Long) {
        val post = postPersistencePort.findById(postId)
        post.id?.let {
            val timelines = followPersistencePort.findAllByFollowMemberId(writerId).map {
                Timeline(memberId = it.followerMemberId, postId = post.id, postedAt = post.createdAt)
            }
            timelinePersistencePort.saveAll(timelines)
        }
    }

    @Transactional
    override fun createByFollow(followerId: Long, followId: Long) {
        val posts = postPersistencePort.findAllByMemberId(followId)
        val alreadyExistPostIds = timelinePersistencePort.findAllByMemberIdAndPostIdIn(followerId, posts.map { it.id!! }).map {
            t -> t.postId
        }
        val timelines = posts.filter {
            !alreadyExistPostIds.contains(it.id)
        }.map {
            Timeline(memberId = followerId, postId = it.id!!, postedAt = it.createdAt)
        }
        timelinePersistencePort.saveAll(timelines)
    }

    @Transactional
    override fun deleteByUnFollow(followerId: Long, followId: Long) {
        val postIds = postPersistencePort.findAllByMemberId(followId).map { it.id!! }
        timelinePersistencePort.deleteAllByMemberIdAndPostIdIn(followerId, postIds)
    }
}