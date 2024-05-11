package dev.hoon.deepdive.heavytraffic.flitter.core.utils

import com.github.f4b6a3.ulid.Ulid
import com.github.f4b6a3.ulid.UlidCreator
import java.util.UUID

object IdGenerator {
    fun generate(): UUID = UlidCreator.getMonotonicUlid().toUuid()

    fun min(): UUID = Ulid.MIN.toUuid()
}
