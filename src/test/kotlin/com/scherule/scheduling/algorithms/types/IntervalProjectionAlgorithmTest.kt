package com.scherule.scheduling.algorithms.types

import com.scherule.scheduling.algorithms.Availability
import com.scherule.scheduling.algorithms.SchedulingSolution
import com.scherule.scheduling.algorithms.types.interval.projection.IntervalProjectionAlgorithm
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import org.joda.time.Duration
import org.joda.time.Interval
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test


internal class IntervalProjectionAlgorithmTest {

    val algorithm = IntervalProjectionAlgorithm()

    @Test
    fun forOnePersonPicksTheLongestOfHisAvailabilityIntervals() {
        assertThat(algorithm.schedule(SchedulingProblemPojo(
                minParticipants = 1,
                minDuration = Duration.standardHours(1),
                between = Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z"),
                availability = setOf(
                        Availability(setOf(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")))
                )
        ))).isEqualToComparingFieldByField(SchedulingSolution(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")))
    }

    @Test
    fun forTwoRequiredPeopleUsesMaximumCommonTimeInterval() {
        assertThat(algorithm.schedule(SchedulingProblemPojo(
                minParticipants = 2,
                minDuration = Duration.standardHours(1),
                between = Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                availability = setOf(
                        Availability(setOf(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")))
                )
        ))).isEqualToComparingFieldByField(SchedulingSolution(Interval(2)))
    }

}