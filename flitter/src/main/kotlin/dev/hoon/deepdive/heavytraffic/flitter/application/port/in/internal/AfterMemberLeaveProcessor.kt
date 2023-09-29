package dev.hoon.deepdive.heavytraffic.flitter.application.port.`in`.internal // ktlint-disable package-name

import java.util.UUID

/**
 * 회원탈퇴 후처리
 */
fun interface AfterMemberLeaveProcessor {
    /**
     * 실행
     *
     * 1. 탈퇴 회원의 작성글을 참조하는 타임라인 제거
     * 2. 탈퇴 회원을 팔로우하는 팔로잉 제거
     * 3. 탈퇴 회원이 누른 좋아요 제거
     * 4. 탈퇴 회원의 작성글 제거
     * 5. 탈퇴 회원의 타임라인 제거
     *
     * @param memberId 탈퇴 회원 아이디
     */
    fun execute(memberId: UUID)
}
