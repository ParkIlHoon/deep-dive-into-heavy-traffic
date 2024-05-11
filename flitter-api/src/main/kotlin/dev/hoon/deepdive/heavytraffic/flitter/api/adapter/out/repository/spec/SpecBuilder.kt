package dev.hoon.deepdive.heavytraffic.flitter.api.adapter.out.repository.spec

import org.springframework.data.jpa.domain.Specification
import java.util.function.Function
import java.util.function.Supplier

class SpecBuilder {
    companion object {
        fun <T> builder(type: Class<T>): Builder<T> = Builder()
    }

    class Builder<T> {
        private val specs: MutableList<Specification<T>> = mutableListOf()

        fun toSpec(): Specification<T> {
            val spec: Specification<T> = Specification.where(null)
            specs.forEach { spec.and(it) }
            return spec
        }
        fun and(spec: Specification<T>): Builder<T> {
            specs.add(spec)
            return this
        }

        fun ifHasText(str: String?, specSupplier: Function<String, Specification<T>>): Builder<T> {
            if (!str.isNullOrBlank()) {
                specs.add(specSupplier.apply(str))
            }
            return this
        }

        fun ifTrue(cond: Boolean, specSupplier: Supplier<Specification<T>>): Builder<T> {
            if (cond) {
                specs.add(specSupplier.get())
            }
            return this
        }

    }
}