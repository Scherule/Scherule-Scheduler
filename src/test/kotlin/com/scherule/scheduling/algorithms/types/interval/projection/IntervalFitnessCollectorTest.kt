package com.scherule.scheduling.algorithms.types.interval.projection

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Instant
import org.joda.time.Interval
import java.util.stream.Stream

internal class IntervalFitnessCollectorTest {

    companion object {
        val POSITIVE_FITNESS = 1
    }

    @Test
    fun collectsSingleIntervalOutOfTwoNonNullInstants() {
        assertThat(
                Stream.of(
                        instantFitness("2017-10-03T14:15Z", POSITIVE_FITNESS),
                        instantFitness("2017-10-03T14:15Z", POSITIVE_FITNESS)
                ).collect(IntervalFitnessCollector())
        ).containsExactly(
                intervalFitness("2017-10-03T14:15Z/2017-10-03T14:15Z", POSITIVE_FITNESS)
        )
    }

    private fun intervalFitness(interval: String, fitness: Int)
            = IntervalFitness(Interval.parse(interval), Fitness(fitness))

    private fun instantFitness(instant: String, fitness: Int)
            = InstantFitness(Instant.parse(instant), Fitness(fitness))

}