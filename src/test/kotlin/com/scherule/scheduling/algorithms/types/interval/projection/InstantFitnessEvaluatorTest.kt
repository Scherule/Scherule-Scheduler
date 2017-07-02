package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class InstantFitnessEvaluatorTest {

    val instantFitnessEvaluator = InstantFitnessEvaluator()

    @Test
    fun instantWithTwoAvailabilitiesOfOneHourScoresTwo() {
        val instant = Instant.parse("2017-10-03T14:15Z")
        assertThat(
                InstantAvailability(
                        instant,
                        setOf(Participation(setOf(Interval.parse("2017-10-03T14:00Z/2017-10-03T16:00Z"))))
                ).evaluateBy(instantFitnessEvaluator)
        ).isEqualTo(InstantFitness(instant, Fitness(2)))
    }

}