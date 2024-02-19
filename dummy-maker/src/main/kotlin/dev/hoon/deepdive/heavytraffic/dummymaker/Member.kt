package dev.hoon.deepdive.heavytraffic.dummymaker

import com.github.f4b6a3.ulid.UlidCreator
import java.time.LocalDate

fun main() {
    println("INSERT INTO member")
    println("(id, email, nickname, birth_day, created_at, updated_at)")
    println("VALUES")
    for (i in 1..100000) {
        val id = UlidCreator.getMonotonicUlid().toUuid().toString()
        val email = "Dummy$i@flitter.dev"
        val nickname = "Dummy$i"
        val birthDay = LocalDate.now().minusDays(i.toLong()).toString()

        val script = if (i == 1) {
            "  ('$id', '$email', '$nickname', '$birthDay', NOW(), NOW())"
        } else {
            "  , ('$id', '$email', '$nickname', '$birthDay', NOW(), NOW())"
        }

        println(script)
    }
}