package com.scherule.scheduling.algorithms.types.interval.projection

import org.assertj.core.api.Assertions.*
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class FitIntervalTest {

    companion object {
        val ANY_FITNESS = Fitness(100)
    }

    @Test
    fun extendingFitIntervalWithInstantBeforeIntervalExpandsFitIntervalStartToContainIt() {
        val currentFitInterval = FitInterval(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"), ANY_FITNESS)
        assertThat(currentFitInterval.expandTo(Instant("2017-10-03T19:00Z"), ANY_FITNESS))
                .isEqualTo(FitInterval(Interval.parse("2017-10-03T14:15Z/2017-10-03T19:00Z"), ANY_FITNESS))
    }

    @Test
    fun extendingFitIntervalWithInstantAfterIntervalExpandsFitIntervalEndToContainIt() {
        val currentFitInterval = FitInterval(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"), ANY_FITNESS)
        assertThat(currentFitInterval.expandTo(Instant("2017-10-03T11:00Z"), ANY_FITNESS))
                .isEqualTo(FitInterval(Interval.parse("2017-10-03T11:00Z/2017-10-03T16:00Z"), ANY_FITNESS))
    }

    @Test
    fun extendingFitIntervalWithSmallerFitnessLowersFitnessToMatchIt() {
        val currentFitInterval = FitInterval(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"), Fitness(100))
        assertThat(currentFitInterval.expandTo(Instant("2017-10-03T15:00Z"), Fitness(50)))
                .isEqualTo(FitInterval(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"), Fitness(50)))
    }

}