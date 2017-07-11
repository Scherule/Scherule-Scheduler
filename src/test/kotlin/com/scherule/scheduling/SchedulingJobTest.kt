package com.scherule.scheduling

import com.scherule.scheduling.algorithms.Participant
import com.scherule.scheduling.algorithms.types.interval.projection.Availability
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTimeZone
import org.joda.time.Duration
import org.joda.time.Interval
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SchedulingJobTest {

    companion object {

        val jobContent = "{\"id\":{\"id\":\"933\"},\"algorithm\":{\"type\":\"intervalProjection\"},\"parameters\":{\"between\":\"1507040100000-1507046400000\",\"minDuration\":18000000,\"minParticipants\":3},\"participants\":[{\"id\":{\"id\":\"321\"},\"name\":\"Greg\",\"importance\":99,\"availability\":[{\"interval\":\"1507040100000-1507046400000\",\"preference\":2},{\"interval\":\"1506940100000-1506946400000\",\"preference\":1}]}]}"

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            DateTimeZone.setDefault(DateTimeZone.UTC)
        }

    }

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

    @Test
    fun participantsCanBeRead() {
        assertThat(SchedulingJob(jobContent).getParticipants()).containsExactlyInAnyOrder(
                Participant("321", 99, setOf(
                        Availability(Interval.parse("2017-10-03T14:15:00.000Z/2017-10-03T16:00:00.000Z"), 2),
                        Availability(Interval.parse("2017-10-02T10:28:20.000Z/2017-10-02T12:13:20.000Z"), 1)
                ))
        )
    }

}