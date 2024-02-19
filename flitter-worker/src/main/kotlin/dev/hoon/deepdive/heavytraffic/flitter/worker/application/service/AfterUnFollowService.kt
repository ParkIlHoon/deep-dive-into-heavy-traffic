package dev.hoon.deepdive.heavytraffic.flitter.worker.application.service

import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.exception.CannotUnFollowException
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.FollowPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.out.MemberPort
import dev.hoon.deepdive.heavytraffic.flitter.worker.application.port.`in`.AfterUnFollowProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class AfterUnFollowService(
    private val memberPort: MemberPort,
    private val followPort: FollowPort,
) : AfterUnFollowProcessor {
    @Transactional
    override fun execute(followerId: UUID, followId: UUID) {
        // 1. 팔로워 유효성 체크
        validateMember(followerId) { CannotUnFollowException(it) }

        // 2. 팔로잉 제거
        followPort.delete(followerId, followId)
    }

    private fun validateMember(memberId: UUID, thrower: (Exception) -> Exception) {
        try {
            memberPort.get(memberId)
        } catch (e: Exception) {
            throw thrower(e)
        }
    }
}