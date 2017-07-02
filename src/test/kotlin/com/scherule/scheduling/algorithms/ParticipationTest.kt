package com.scherule.scheduling.algorithms

import org.assertj.core.api.Assertions.*
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class ParticipationTest {

    @Test
    fun onlyWithinNarrowsNonOverlappingIntervals() {
        assertThat(Participation(setOf(Interval.parse("2017-10-01T14:15Z/2017-10-05T19:00Z")))
                .onlyWithin(Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z")))
                .isEqualTo(Participation(setOf(Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z"))))
    }

    @Test
    fun filtersOutIntervalsOutsideBetweenInterval() {
        assertThat(Participation(setOf(
                Interval.parse("2017-11-01T14:15Z/2017-11-05T19:00Z"),
                Interval.parse("2017-10-01T14:15Z/2017-10-05T19:00Z"),
                Interval.parse("2017-09-01T14:15Z/2017-09-05T19:00Z")
        ))
                .onlyWithin(Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z")))
                .isEqualTo(Participation(setOf(Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z"))))
    }

}