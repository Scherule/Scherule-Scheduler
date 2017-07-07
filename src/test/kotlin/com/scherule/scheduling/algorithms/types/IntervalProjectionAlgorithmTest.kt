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
                        participationWithAvailabilities("1", Availability(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")))
                )
        ))).isEqualTo(SchedulingSolution(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")))
    }

    @Test
    fun picksUniqueCommonInterval() {
        assertThat(algorithm.schedule(SchedulingProblemPojo(
                minParticipants = 3,
                minDuration = Duration.standardHours(1),
                between = Interval.parse("2017-10-02T14:00Z/2017-10-06T16:00Z"),
                participation = setOf(
                        participationWithAvailabilities("1", Availability(Interval.parse("2017-10-02T14:00Z/2017-10-03T18:00Z"))),
                        participationWithAvailabilities("2", Availability(Interval.parse("2017-10-03T15:00Z/2017-10-03T19:00Z"))),
                        participationWithAvailabilities("3", Availability(Interval.parse("2017-10-03T14:15Z/2017-10-03T17:30Z")))
                )
        ))).isEqualTo(SchedulingSolution(Interval.parse("2017-10-03T15:00Z/2017-10-03T17:30Z")))
    }

    @Test
    fun picksLongerIntervalFromTwoPossible() {
        assertThat(algorithm.schedule(SchedulingProblemPojo(
                minParticipants = 3,
                minDuration = Duration.standardHours(1),
                between = Interval.parse("2017-10-02T14:00Z/2017-10-06T16:00Z"),
                participation = setOf(
                        participationWithAvailabilities(
                                "1",
                                Availability(Interval.parse("2017-10-02T19:00Z/2017-10-02T23:00Z")),
                                Availability(Interval.parse("2017-10-02T14:00Z/2017-10-03T15:00Z"))
                        ),
                        participationWithAvailabilities("2", Availability(Interval.parse("2017-10-01T15:00Z/2017-10-30T19:00Z")))
                )
        ))).isEqualTo(SchedulingSolution(Interval.parse("2017-10-02T14:00Z/2017-10-03T15:00Z")))
    }

    @Test
    fun pickIntervalWithAllRequiredParticipantsEvenAtTheExpenseOfOthers() {
        assertThat(algorithm.schedule(SchedulingProblemPojo(
                minParticipants = 1,
                minDuration = Duration.standardHours(1),
                between = Interval.parse("2017-09-30T14:00Z/2017-10-06T16:00Z"),
                participation = setOf(
                        participationWithAvailabilities(
                                "1",
                                Availability(Interval.parse("2017-10-01T14:00Z/2017-10-01T23:00Z"))
                        ),
                        participationWithAvailabilities(
                                id = "2", importance = Int.MAX_VALUE,
                                availability = Availability(Interval.parse("2017-10-02T16:00Z/2017-10-02T18:00Z"))
                        )
                )
        ))).isEqualTo(SchedulingSolution(Interval.parse("2017-10-02T16:00Z/2017-10-02T18:00Z")))
    }

    private fun participationWithAvailabilities(id: String, vararg availability: Availability, importance: Int = 1): Participation {
        return Participation(
                participationId = "1",
                importance = importance,
                availabilities = availability.toSet()
        )
    }

}