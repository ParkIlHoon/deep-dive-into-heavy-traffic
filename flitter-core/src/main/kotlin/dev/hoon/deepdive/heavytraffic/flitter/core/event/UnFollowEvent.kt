@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.core.event

import java.util.*

/**
 * 언팔로우 이벤트
 *
 * @property followerMemberId 팔로워 멤버 아이디
 * @property memberId 언팔로우 대상 멤버 아이디
 */
data class UnFollowEvent(
    val followerMemberId: UUID,
    val memberId: UUID,
)
