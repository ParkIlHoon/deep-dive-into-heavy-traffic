package dev.hoon.deepdive.heavytraffic.flitter.api.domain.follow

import java.time.LocalDateTime
import java.util.UUID

/**
 * 팔로우
 *
 * @property memberId 팔로우 멤버 아이디
 * @property followerMemberId 팔로워 멤버 아이디
 * @property createdAt 생성일시
 */
class Follow(
    val id: UUID? = null,
    val memberId: UUID,
    val followerMemberId: UUID,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
