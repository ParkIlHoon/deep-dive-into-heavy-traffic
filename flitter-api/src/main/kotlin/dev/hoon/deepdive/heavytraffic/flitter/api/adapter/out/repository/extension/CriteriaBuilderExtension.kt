package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.extension

import jakarta.persistence.criteria.CriteriaBuilder

/**
 * CriteriaBuilder 확장 함수
 *
 * @see CriteriaBuilder
 */

/**
 * IN values
 *
 * IN 조건 컬렉션 타입 value 지정을 위한 확장 함수
 *
 * @param values IN 조건 컬렉션 값
 */
@Suppress("ktlint:standard:no-consecutive-comments")
fun <T> CriteriaBuilder.In<T>.values(values: Collection<T>): CriteriaBuilder.In<T> {
    for (v in values) {
        this.value(v)
    }
    return this
}
