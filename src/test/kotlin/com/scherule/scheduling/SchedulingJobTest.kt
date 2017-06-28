package com.scherule.scheduling

import org.joda.time.Duration
import org.joda.time.Interval
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

internal class SchedulingJobTest {

    val jobContent = "{\"id\":{\"id\":\"933\"},\"parameters\":{\"between\":\"1507040100000-1507046400000\",\"minDuration\":18000000,\"minParticipants\":3},\"participants\":[{\"id\":{\"id\":\"321\"},\"name\":\"Greg\",\"importance\":1,\"availability\":[\"1507040100000-1507046400000\"]}]}"

    @Test
    fun minParticipantsCanBeRead() {
        assertEquals(
                3,
                SchedulingJob(jobContent).getMinParticipants()
        )
    }

    @Test
    fun minDurationCanBeRead() {
        assertEquals(
                Duration.millis(18000000),
                SchedulingJob(jobContent).getMinDuration()
        )
    }

    @Test
    fun betweenCanBeRead() {
        assertEquals(
                Interval(1507040100000, 1507046400000),
                SchedulingJob(jobContent).getBetween()
        )
    }

}