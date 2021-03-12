package com.zachvg.chessclock.clock

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import kotlin.random.Random

class ClockUtilTest {
    private var millis = 0L

    // Converts a time given in hours, minutes, and seconds to milliseconds
    private fun setMillisFromHoursMinutesSeconds(hours: Long, minutes: Long, seconds: Long) {
        millis = hours * 3_600_000 + minutes * 60_000 + seconds * 1_000
    }

    private fun setMillisFromHourMinutesSecondsWithRandomOffset(hours: Long, minutes: Long, seconds: Long) {
        setMillisFromHoursMinutesSeconds(hours, minutes, seconds)

        // Adds a random offset in the range [0, 999]
        millis += Random.nextLong(0, 1000)
    }

    // Perfect intervals
    // These tests always fall on a multiple of 1,000 ms
    @Test
    fun millisToTimeString_Input_0h0m0s() {
        setMillisFromHoursMinutesSeconds(0, 0, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("0:00")
    }

    @Test
    fun millisToTimeString_Input_0h0m5s() {
        setMillisFromHoursMinutesSeconds(0, 0, 5)
        assertThat(millisToTimeString(millis)).isEqualTo("0:05")

    }

    @Test
    fun millisToTimeString_Input_0h0m10s() {
        setMillisFromHoursMinutesSeconds(0,0, 10)
        assertThat(millisToTimeString(millis)).isEqualTo("0:10")

    }

    @Test
    fun millisToTimeString_Input_0h0m30s() {
        setMillisFromHoursMinutesSeconds(0,0, 30)
        assertThat(millisToTimeString(millis)).isEqualTo("0:30")

    }

    @Test
    fun millisToTimeString_Input_0h1m0s() {
        setMillisFromHoursMinutesSeconds(0,1, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("1:00")

    }

    @Test
    fun millisToTimeString_Input_0h1m4s() {
        setMillisFromHoursMinutesSeconds(0, 1, 4)
        assertThat(millisToTimeString(millis)).isEqualTo("1:04")
    }

    @Test
    fun millisToTimeString_Input_0h1m30s() {
        setMillisFromHoursMinutesSeconds(0,1, 30)
        assertThat(millisToTimeString(millis)).isEqualTo("1:30")

    }

    @Test
    fun millisToTimeString_Input_0h7m38s() {
        setMillisFromHoursMinutesSeconds(0,7, 38)
        assertThat(millisToTimeString(millis)).isEqualTo("7:38")

    }

    @Test
    fun millisToTimeString_Input_0h10m0s() {
        setMillisFromHoursMinutesSeconds(0,10, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("10:00")

    }

    @Test
    fun millisToTimeString_Input_0h11m45s() {
        setMillisFromHoursMinutesSeconds(0,11, 45)
        assertThat(millisToTimeString(millis)).isEqualTo("11:45")

    }

    @Test
    fun millisToTimeString_Input_0h15m0s() {
        setMillisFromHoursMinutesSeconds(0,15, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("15:00")
    }

    @Test
    fun millisToTimeString_Input_0h23m07s() {
        setMillisFromHoursMinutesSeconds(0,23, 7)
        assertThat(millisToTimeString(millis)).isEqualTo("23:07")
    }

    @Test
    fun millisToTimeString_Input_0h49m9s() {
        setMillisFromHoursMinutesSeconds(0,49, 9)
        assertThat(millisToTimeString(millis)).isEqualTo("49:09")
    }

    @Test
    fun millisToTimeString_Input_1h0m0s() {
        setMillisFromHoursMinutesSeconds(1,0, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("1:00:00")
    }

    @Test
    fun millisToTimeString_Input_1h4m3s() {
        setMillisFromHoursMinutesSeconds(1,4, 3)
        assertThat(millisToTimeString(millis)).isEqualTo("1:04:03")
    }

    @Test
    fun millisToTimeString_Input_1h28m27s() {
        setMillisFromHoursMinutesSeconds(1,28, 27)
        assertThat(millisToTimeString(millis)).isEqualTo("1:28:27")
    }

    @Test
    fun millisToTimeString_Input_2h3m4s() {
        setMillisFromHoursMinutesSeconds(2, 3, 4)
        assertThat(millisToTimeString(millis)).isEqualTo("2:03:04")
    }

    @Test
    fun millisToTimeString_Input_9h34m23s() {
        setMillisFromHoursMinutesSeconds(9, 34, 23)
        assertThat(millisToTimeString(millis)).isEqualTo("9:34:23")
    }

    @Test
    fun millisToTimeString_Input_10h0m0s() {
        setMillisFromHoursMinutesSeconds(10, 0, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("10:00:00")
    }

    @Test
    fun millisToTimeString_Input_10h0m7s() {
        setMillisFromHoursMinutesSeconds(10, 0, 7)
        assertThat(millisToTimeString(millis)).isEqualTo("10:00:07")
    }

    @Test
    fun millisToTimeString_Input_10h0m23s() {
        setMillisFromHoursMinutesSeconds(10, 0, 23)
        assertThat(millisToTimeString(millis)).isEqualTo("10:00:23")
    }

    @Test
    fun millisToTimeString_Input_10h1m0s() {
        setMillisFromHoursMinutesSeconds(10, 1, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("10:01:00")
    }

    @Test
    fun millisToTimeString_Input_10h3m5s() {
        setMillisFromHoursMinutesSeconds(10, 3, 5)
        assertThat(millisToTimeString(millis)).isEqualTo("10:03:05")
    }

    @Test
    fun millisToTimeString_Input_10h12m0s() {
        setMillisFromHoursMinutesSeconds(10, 12, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("10:12:00")
    }

    @Test
    fun millisToTimeString_Input_10h14m3s() {
        setMillisFromHoursMinutesSeconds(10, 14, 3)
        assertThat(millisToTimeString(millis)).isEqualTo("10:14:03")
    }

    @Test
    fun millisToTimeString_Input_10h16m13s() {
        setMillisFromHoursMinutesSeconds(10, 16, 13)
        assertThat(millisToTimeString(millis)).isEqualTo("10:16:13")
    }

    // Imperfect intervals
    // These tests add a random offset to a perfect interval to check that
    // the millisecond values in between seconds will still give the same
    // expected result string.
    // For example, 63,000 and 63,009 and 63,682 should all produce the string 1:03
    // The offset will be in the range [0,999]
    @Test
    fun millisToTimeString_Input_0h0m0s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 0, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("0:00")
    }

    @Test
    fun millisToTimeString_Input_0h0m3s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 0, 3)
        assertThat(millisToTimeString(millis)).isEqualTo("0:03")
    }

    @Test
    fun millisToTimeString_Input_0h0m8s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 0, 8)
        assertThat(millisToTimeString(millis)).isEqualTo("0:08")
    }

    @Test
    fun millisToTimeString_Input_0h0m12s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 0, 12)
        assertThat(millisToTimeString(millis)).isEqualTo("0:12")
    }

    @Test
    fun millisToTimeString_Input_0h0m19s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 0, 19)
        assertThat(millisToTimeString(millis)).isEqualTo("0:19")
    }

    @Test
    fun millisToTimeString_Input_0h0m43s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 0, 43)
        assertThat(millisToTimeString(millis)).isEqualTo("0:43")
    }

    @Test
    fun millisToTimeString_Input_0h1m0s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 1, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("1:00")
    }

    @Test
    fun millisToTimeString_Input_0h2m5s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 2, 5)
        assertThat(millisToTimeString(millis)).isEqualTo("2:05")
    }

    @Test
    fun millisToTimeString_Input_0h4m36s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 4, 36)
        assertThat(millisToTimeString(millis)).isEqualTo("4:36")
    }

    @Test
    fun millisToTimeString_Input_0h8m52s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 8, 52)
        assertThat(millisToTimeString(millis)).isEqualTo("8:52")
    }

    @Test
    fun millisToTimeString_Input_0h10m2s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 10, 2)
        assertThat(millisToTimeString(millis)).isEqualTo("10:02")
    }

    @Test
    fun millisToTimeString_Input_0h11m6s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 11, 6)
        assertThat(millisToTimeString(millis)).isEqualTo("11:06")
    }

    @Test
    fun millisToTimeString_Input_0h14m20s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 14, 20)
        assertThat(millisToTimeString(millis)).isEqualTo("14:20")
    }

    @Test
    fun millisToTimeString_Input_0h32m47s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(0, 32, 47)
        assertThat(millisToTimeString(millis)).isEqualTo("32:47")
    }

    @Test
    fun millisToTimeString_Input_2h0m4s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(2, 0, 4)
        assertThat(millisToTimeString(millis)).isEqualTo("2:00:04")
    }

    @Test
    fun millisToTimeString_Input_5h0m39s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(5, 0, 39)
        assertThat(millisToTimeString(millis)).isEqualTo("5:00:39")
    }

    @Test
    fun millisToTimeString_Input_6h0m0s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(6, 0, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("6:00:00")
    }

    @Test
    fun millisToTimeString_Input_7h1m0s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(7, 1, 0)
        assertThat(millisToTimeString(millis)).isEqualTo("7:01:00")
    }

    @Test
    fun millisToTimeString_Input_8h4m5s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(8, 4, 5)
        assertThat(millisToTimeString(millis)).isEqualTo("8:04:05")
    }

    @Test
    fun millisToTimeString_Input_8h6m41s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(8, 6, 41)
        assertThat(millisToTimeString(millis)).isEqualTo("8:06:41")
    }

    @Test
    fun millisToTimeString_Input_9h15m3s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(9, 15, 3)
        assertThat(millisToTimeString(millis)).isEqualTo("9:15:03")
    }

    @Test
    fun millisToTimeString_Input_9h36m56s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(9, 36, 56)
        assertThat(millisToTimeString(millis)).isEqualTo("9:36:56")
    }

    @Test
    fun millisToTimeString_Input_13h0m4s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(13, 0, 4)
        assertThat(millisToTimeString(millis)).isEqualTo("13:00:04")
    }

    @Test
    fun millisToTimeString_Input_16h0m56s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(16, 0, 56)
        assertThat(millisToTimeString(millis)).isEqualTo("16:00:56")
    }

    @Test
    fun millisToTimeString_Input_23h4m8s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(23, 4, 8)
        assertThat(millisToTimeString(millis)).isEqualTo("23:04:08")
    }

    @Test
    fun millisToTimeString_Input_32h7m26s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(32, 7, 26)
        assertThat(millisToTimeString(millis)).isEqualTo("32:07:26")
    }

    @Test
    fun millisToTimeString_Input_41h13m4s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(41, 13, 4)
        assertThat(millisToTimeString(millis)).isEqualTo("41:13:04")
    }

    @Test
    fun millisToTimeString_Input_67h18m47s_PlusRandomOffset() {
        setMillisFromHourMinutesSecondsWithRandomOffset(67, 18, 47)
        assertThat(millisToTimeString(millis)).isEqualTo("67:18:47")
    }
}