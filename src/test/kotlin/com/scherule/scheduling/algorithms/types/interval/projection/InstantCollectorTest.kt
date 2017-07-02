package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Duration
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class InstantCollectorTest {

    @Test
    fun mapsSingleIntervalToTwoInstants() {
        assertThat(InstantCollector(problemWithParticipations(setOf(
                participationWithIntervals(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                )
        ))).getInstants())
                .containsExactly(
                        Instant.parse("2017-10-03T14:15Z"),
                        Instant.parse("2017-10-03T16:00Z")
                )
    }

    @Test
    fun mapsTwoNonOverlappingIntervalsToFourInstants() {
        assertThat(InstantCollector(problemWithParticipations(setOf(
                participationWithIntervals(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                        Interval.parse("2017-10-03T15:45Z/2017-10-03T17:00Z")
                )
        ))).getInstants())
                .containsExactly(
                        Instant.parse("2017-10-03T14:15Z"),
                        Instant.parse("2017-10-03T16:00Z"),
                        Instant.parse("2017-10-04T15:45Z"),
                        Instant.parse("2017-10-04T17:35Z")
                )
    }

    @Test
    fun mapsTwoOverlappingIntervalsToThreeInstants() {
        assertThat(InstantCollector(problemWithParticipations(setOf(
                participationWithIntervals(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T19:00Z")
                )
        ))).getInstants())
                .containsExactly(
                        Instant.parse("2017-10-03T14:15Z"),
                        Instant.parse("2017-10-03T16:00Z"),
                        Instant.parse("2017-10-03T19:00Z")
                )
    }

    private fun problemWithParticipations(participations: Set<Participation>): SchedulingProblemPojo {
        return SchedulingProblemPojo(
                1,
                Duration.standardHours(2),
                Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                participations
        )
    }


    private fun participationWithIntervals(vararg intervals: Interval): Participation {
        return Participation(
                participationId = "1",
                importance = 1,
                availabilities = intervals.map {
                    Availability(1, it)
                }.toSet()
        )
    }

}