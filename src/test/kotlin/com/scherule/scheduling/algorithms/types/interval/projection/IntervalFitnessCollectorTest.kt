package com.scherule.scheduling.algorithms.types.interval.projection

import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTimeZone
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.stream.Stream

class IntervalFitnessCollectorTest {

    companion object {
        val POSITIVE_FITNESS = 1
        val NEGATIVE_FITNESS = -1

        @BeforeAll
        @JvmStatic
        fun beforeClass() {
            DateTimeZone.setDefault(DateTimeZone.UTC)
        }
    }

    @Test
    fun collectsSingleIntervalOutOfTwoNonNullInstants() {
        assertThat(
                Stream.of(
                        instantFitness("2017-10-03T14:00Z", POSITIVE_FITNESS),
                        instantFitness("2017-10-03T16:15Z", POSITIVE_FITNESS)
                ).collect(IntervalFitnessCollector())
        ).containsExactly(
                intervalFitness("2017-10-03T14:00Z/2017-10-03T16:15Z", POSITIVE_FITNESS)
        )
    }

    @Test
    fun collectsTwoIntervalsOutOfFiveInstantsWhenMiddleOneIsNull() {
        assertThat(
                Stream.of(
                        instantFitness("2017-10-03T14:00Z", POSITIVE_FITNESS),
                        instantFitness("2017-10-03T15:00Z", POSITIVE_FITNESS),
                        instantFitness("2017-10-03T16:00Z", NEGATIVE_FITNESS),
                        instantFitness("2017-10-03T17:00Z", POSITIVE_FITNESS),
                        instantFitness("2017-10-03T18:00Z", POSITIVE_FITNESS)
                ).collect(IntervalFitnessCollector())
        ).containsExactly(
                intervalFitness("2017-10-03T14:00Z/2017-10-03T15:00Z", POSITIVE_FITNESS),
                intervalFitness("2017-10-03T17:00Z/2017-10-03T18:00Z", POSITIVE_FITNESS)
        )
    }

    private fun intervalFitness(interval: String, fitness: Int)
            = IntervalFitness(Interval.parse(interval), Fitness(fitness))

    private fun instantFitness(instant: String, fitness: Int)
            = InstantFitness(Instant.parse(instant), Fitness(fitness))

}