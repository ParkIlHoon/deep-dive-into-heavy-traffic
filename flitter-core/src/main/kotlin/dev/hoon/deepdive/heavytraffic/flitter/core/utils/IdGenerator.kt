package dev.hoon.deepdive.heavytraffic.flitter.core.utils

import com.github.f4b6a3.ulid.Ulid
import com.github.f4b6a3.ulid.UlidCreator

object IdGenerator {
    fun generate() = UlidCreator.getMonotonicUlid().toUuid()

    fun min() = Ulid.MIN.toUuid()
}