package dev.hoon.deepdive.heavytraffic.flitter.domain.follow

import java.time.LocalDateTime

/**
 * 팔로우
 *
 * @property followMemberId 팔로우 멤버 아이디
 * @property followerMemberId 팔로워 멤버 아이디
 * @property createdAt 생성일시
 */
class Follow(
    val followMemberId: Long,
    val followerMemberId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now()
)