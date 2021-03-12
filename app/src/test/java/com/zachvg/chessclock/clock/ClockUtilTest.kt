package com.zachvg.chessclock.clock

import org.junit.Test
import com.google.common.truth.Truth.assertThat

class ClockUtilTest {
    private var millis = 0L

    // Converts a time given in hours, minutes, and seconds to milliseconds
    private fun setMillisFromHoursMinutesSeconds(hours: Long, minutes: Long, seconds: Long) {
        millis = hours * 3_600_000 + minutes * 60_000 + seconds * 1_000
    }

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
        setMillisFromHoursMinutesSeconds(10, 0, 4)
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

}