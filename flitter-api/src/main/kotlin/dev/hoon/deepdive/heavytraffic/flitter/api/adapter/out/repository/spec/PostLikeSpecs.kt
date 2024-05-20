package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.spec

import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.PostLike_
import org.springframework.data.jpa.domain.Specification
import java.util.UUID

object PostLikeSpecs {
    fun memberId(memberId: UUID): Specification<PostLike> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.equal(root.get(PostLike_.memberId), memberId)
        }
}
