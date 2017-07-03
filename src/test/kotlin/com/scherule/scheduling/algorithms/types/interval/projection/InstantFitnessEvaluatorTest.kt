package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.types.interval.projection.ProblemUtils.problemWithParticipations
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class InstantFitnessEvaluatorTest {

    @Test
    fun instantWithTwoAvailabilitiesOfOneHourScoresTwo() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(problemWithParticipations(setOf(
                ProblemUtils.participationWithIntervals(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                        Interval.parse("2017-10-03T15:45Z/2017-10-03T17:00Z")
                )
        )))
        val instant = Instant.parse("2017-10-03T14:15Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness(1)))
    }

    @Test
    fun instantMatchingEndOfIntervalScores() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(problemWithParticipations(setOf(
                ProblemUtils.participationWithIntervals(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")
                )
        )))
        val instant = Instant.parse("2017-10-03T16:00Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness(1)))
    }

    @Test
    fun instantEarlierThanStartOfIntervalScoresZero() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(problemWithParticipations(setOf(
                ProblemUtils.participationWithIntervals(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")
                )
        )))
        val instant = Instant.parse("2017-10-03T14:14Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness.ZERO_FITNESS))
    }

    @Test
    fun instantLaterThanEndOfIntervalScoresZero() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(problemWithParticipations(setOf(
                ProblemUtils.participationWithIntervals(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")
                )
        )))
        val instant = Instant.parse("2017-10-03T16:01Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness.ZERO_FITNESS))
    }

}