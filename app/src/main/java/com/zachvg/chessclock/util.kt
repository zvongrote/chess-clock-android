package com.zachvg.chessclock

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
        /*
        To make sure the correct time is displayed, the number of milliseconds has to be rounded
        up the the closest second before performing the logic to build the string.

        For example:
        10,001 ms should display as 11 seconds
        10,000 ms should display as 10 seconds

        To round up, add 999 ms, divide by 1,000, then multiply by 1,000

        For example:
        10,001 + 999 = 11,000 -> 11,000 / 1,000 = 11 -> 11 * 1,000 = 11,000
        12,364 + 999 = 13,363 -> 13,363 / 1,000 = 13 -> 13 * 1,000 = 13,000
         */
        var localMillis = ((millis + 999) / 1_000) * 1_000

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