package com.zachvg.chessclock.clock

import java.util.concurrent.TimeUnit

/*
Converts a length of time in milliseconds into hours:minutes:seconds
When hours == 0, it's not shown.
Minutes are shown even if 0 and should be padded with a leading zero if hours are shown
Seconds should always be padded with a leading 0
 */
fun millisToTimeString(millis: Long?): String {
    var result = "0:00"

    millis?.let {
        var localMillis = millis

        val hours = TimeUnit.MILLISECONDS.toHours(localMillis)
        localMillis -= TimeUnit.HOURS.toMillis(hours)

        val minutes = TimeUnit.MILLISECONDS.toMinutes(localMillis)
        localMillis -= TimeUnit.MINUTES.toMillis(minutes)

        val seconds = TimeUnit.MILLISECONDS.toSeconds(localMillis)

        val hoursString = if (hours != 0L) "$hours:" else ""

        val minutesString = if (hours == 0L) "$minutes" else "$minutes".padStart(2, '0')

        val secondsString = "$seconds".padStart(2, '0')

        result = "$hoursString$minutesString:$secondsString"
    }

    return result

}