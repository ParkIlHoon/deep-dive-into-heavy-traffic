@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.spec

import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow
import dev.hoon.deepdive.heavytraffic.flitter.domain.follow.Follow_
import org.springframework.data.jpa.domain.Specification
import java.util.*

object FollowSpecs {
    fun followerMemberId(followerMemberId: UUID): Specification<Follow> =
        Specification { root, _, criteriaBuilder,
            ->
            criteriaBuilder
                .equal(root.get(Follow_.followerMemberId), followerMemberId)
        }

    fun memberId(memberId: UUID): Specification<Follow> =
        Specification { root, _, criteriaBuilder,
            ->
            criteriaBuilder
                .equal(root.get(Follow_.memberId), memberId)
        }
}
