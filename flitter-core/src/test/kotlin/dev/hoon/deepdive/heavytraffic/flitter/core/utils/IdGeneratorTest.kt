package dev.hoon.deepdive.heavytraffic.flitter.core.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.stream.LongStream

class IdGeneratorTest {

    @Test
    fun `유니크 아이디 생성`() {
        // given
        val times = Math.random().toLong()

        // when
        val distinctResult = LongStream.range(0, times)
            .mapToObj { IdGenerator.generate() }
            .distinct()
            .count()

        // then
        Assertions.assertEquals(times, distinctResult)
    }
}