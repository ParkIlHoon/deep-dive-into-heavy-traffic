@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.spec

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.extension.values
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline_
import org.springframework.data.jpa.domain.Specification
import java.util.*

object TimelineSpecs {
    fun memberIdIn(memberIds: Collection<UUID>): Specification<Timeline> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.`in`(root.get(Timeline_.memberId)).values(memberIds)
        }

    fun postIdIn(postIds: Collection<UUID>): Specification<Timeline> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.`in`(root.get(Timeline_.postId)).values(postIds)
        }
}
