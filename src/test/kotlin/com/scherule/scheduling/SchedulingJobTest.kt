package com.scherule.scheduling

import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Duration
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class SchedulingJobTest {

    val jobContent = "{\"id\":{\"id\":\"933\"},\"parameters\":{\"between\":\"1507040100000-1507046400000\",\"minDuration\":18000000,\"minParticipants\":3},\"participants\":[{\"id\":{\"id\":\"321\"},\"name\":\"Greg\",\"importance\":1,\"availability\":[\"1507040100000-1507046400000\"]}]}"

    @Test
    fun minParticipantsCanBeRead() {
        assertThat(SchedulingJob(jobContent).getMinParticipants()).isEqualTo(3)
    }

    @Test
    fun minDurationCanBeRead() {
        assertThat(SchedulingJob(jobContent).getMinDuration()).isEqualTo(Duration.millis(18000000))
    }

    @Test
    fun betweenCanBeRead() {
        assertThat(SchedulingJob(jobContent).getBetween()).isEqualTo(Interval(1507040100000, 1507046400000))
    }

}