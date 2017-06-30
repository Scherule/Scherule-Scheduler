package com.scherule.scheduling.algorithms.types.interval.projection

import org.assertj.core.api.Assertions.*
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class FitIntervalTest {

    @Test
    fun extendingFitIntervalExpandsIntervalAndUsesMinOfAvailabilities() {
        val currentFitInterval = FitInterval(Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"), Fitness(100))
        assertThat(currentFitInterval.expandTo(Instant("2017-10-03T19:00Z"), Fitness(50)))
                .isEqualTo(FitInterval(Interval.parse("2017-10-03T14:15Z/2017-10-03T19:00Z"), Fitness(50)))
    }

}