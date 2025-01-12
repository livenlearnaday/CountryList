package io.github.livenlearnaday.domain.util


import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"


fun parseUNIXTimeStampToText(
    datetimeInMillisUTC: String
): String {

    val datetimeInMillisUTCLong = datetimeInMillisUTC.toLongOrNull() ?: return ""
    val localDateTime = parseEpochMilliToLocalDateTime(datetimeInMillisUTCLong) ?: return ""

    return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.ENGLISH).format(localDateTime)

}

private fun parseEpochMilliToLocalDateTime(unixTimeStamp: Long, zoneId: ZoneId = ZoneId.of("Asia/Bangkok")): LocalDateTime? {
    return try {
        Instant.ofEpochSecond(unixTimeStamp)
            .atZone(zoneId)
            .toLocalDateTime()

    } catch (exception: Exception) {
        null
    }
}




