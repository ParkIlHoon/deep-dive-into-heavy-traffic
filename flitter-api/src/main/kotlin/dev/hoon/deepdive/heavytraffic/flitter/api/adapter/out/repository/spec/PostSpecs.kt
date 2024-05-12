package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.spec

import dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.extension.values
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post
import dev.hoon.deepdive.heavytraffic.flitter.domain.post.Post_
import org.springframework.data.jpa.domain.Specification
import java.util.UUID

object PostSpecs {
    fun postIdIn(ids: Collection<UUID>): Specification<Post> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.`in`(root.get(Post_.id)).values(ids)
        }

    fun writerId(writerId: UUID): Specification<Post> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.equal(root.get(Post_.writerId), writerId)
        }
}
