package dev.hoon.deepdive.heavytraffic.flitter.worker.adapter.exception

import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterErrorCode
import dev.hoon.deepdive.heavytraffic.flitter.core.exception.FlitterException

class FlitterApiException(
    resultCode: Int,
    resultMessage: String?,
) : FlitterException(FlitterErrorCode.valueOf(resultCode), resultMessage)
