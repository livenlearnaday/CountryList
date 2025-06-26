package io.github.livenlearnaday.presentation.util

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun nowInUTCMilliSecondsString(): String {
    val now: Instant = Clock.System.now() // Current instant in UTC
    val nowInUTCMilliSeconds = now.toEpochMilliseconds().div(1000)
    return nowInUTCMilliSeconds.toString()
}
