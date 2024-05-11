package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository

import dev.hoon.deepdive.heavytraffic.flitter.domain.UUIDPrimaryKey
import dev.hoon.deepdive.heavytraffic.flitter.domain.timeline.Timeline
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimelineRepository : JpaRepository<Timeline, UUIDPrimaryKey>, TimelineCustomRepository {
    /**
     * 삭제
     *
     * @param spec 삭제 조건
     * @return 삭제 건수
     */
    fun delete(spec: Specification<Timeline>): Long
}
