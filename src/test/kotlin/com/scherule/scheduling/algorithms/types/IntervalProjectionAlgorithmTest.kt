package com.scherule.scheduling.algorithms.types

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.SchedulingSolution
import com.scherule.scheduling.algorithms.types.interval.projection.Availability
import com.scherule.scheduling.algorithms.types.interval.projection.IntervalProjectionAlgorithm
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Duration
import org.joda.time.Interval
import org.junit.jupiter.api.Test


internal class IntervalProjectionAlgorithmTest {

    val algorithm = IntervalProjectionAlgorithm()

    @Test
    fun forOnePersonPicksTheLongestOfHisAvailabilityIntervals() {
        assertThat(algorithm.schedule(SchedulingProblemPojo(
                minParticipants = 1,
                minDuration = Duration.standardHours(1),
                between = Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z"),
                participation = setOf(
                        Participation(
                                participationId = "1",
                                importance = 1,
                                availabilities = setOf(
                                        Availability(1, Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"))
                                )
                        )
                )
        ))).isEqualTo(SchedulingSolution(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")))
    }

}