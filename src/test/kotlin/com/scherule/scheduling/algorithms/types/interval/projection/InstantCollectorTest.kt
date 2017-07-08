package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.types.interval.projection.ProblemUtils.participantWithAvailability
import com.scherule.scheduling.algorithms.types.interval.projection.ProblemUtils.meetingWithParticipants
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class InstantCollectorTest {

    @Test
    fun mapsSingleIntervalToTwoInstants() {
        assertThat(InstantCollector(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                intervals = "2017-10-03T14:15Z/2017-10-03T16:00Z"
                        )
                )
        ).getInstants())
                .containsExactly(
                        Instant.parse("2017-10-03T14:15Z"),
                        Instant.parse("2017-10-03T16:00Z")
                )
    }

    @Test
    fun mapsTwoNonOverlappingIntervalsToFourInstants() {
        assertThat(InstantCollector(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                intervals = *arrayOf(
                                        "2017-10-03T14:15Z/2017-10-03T16:00Z",
                                        "2017-10-05T16:15Z/2017-10-06T17:00Z"
                                )
                        )
                )
        ).getInstants())
                .containsExactly(
                        Instant.parse("2017-10-03T14:15Z"),
                        Instant.parse("2017-10-03T16:00Z"),
                        Instant.parse("2017-10-05T16:15Z"),
                        Instant.parse("2017-10-06T17:00Z")
                )
    }

    @Test
    fun mapsTwoOverlappingIntervalsToThreeInstants() {
        assertThat(InstantCollector(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                intervals = *arrayOf(
                                        "2017-10-03T14:15Z/2017-10-03T16:00Z",
                                        "2017-10-03T16:00Z/2017-10-06T17:00Z"
                                )
                        )
                )
        ).getInstants())
                .containsExactly(
                        Instant.parse("2017-10-03T14:15Z"),
                        Instant.parse("2017-10-03T16:00Z"),
                        Instant.parse("2017-10-06T17:00Z")
                )
    }


}