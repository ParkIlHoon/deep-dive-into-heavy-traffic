@file:Suppress("ktlint:standard:no-wildcard-imports")

package dev.hoon.deepdive.heavytraffic.flitter.core.constants

import java.time.format.DateTimeFormatter
import java.util.*

class DateConstants {
    companion object {
        const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
        val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.KOREA)
    }
}
