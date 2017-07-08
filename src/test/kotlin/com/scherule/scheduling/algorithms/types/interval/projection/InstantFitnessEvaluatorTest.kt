package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.types.interval.projection.ProblemUtils.meetingWithParticipants
import com.scherule.scheduling.algorithms.types.interval.projection.ProblemUtils.participantWithAvailability
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test

internal class InstantFitnessEvaluatorTest {

    @Test
    fun instantMatchesIntervalAtItsStart() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                intervals = "2017-10-03T14:15Z/2017-10-03T15:15Z"
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T14:15Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness(1)))
    }

    @Test
    fun instantMatchesIntervalAtItsEnd() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                intervals = "2017-10-03T14:15Z/2017-10-03T15:15Z"
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T15:15Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness(1)))
    }

    @Test
    fun fitnessOfAnInstantWhichMatchesTheThreeHoursLongIntervalIsThree() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                intervals = "2017-10-03T14:00Z/2017-10-03T17:00Z"
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T14:30Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness(3)))
    }

    @Test
    fun instantEarlierThanStartOfIntervalScoresZeroForOptionalParticipant() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                importance = 0,
                                intervals = "2017-10-03T14:00Z/2017-10-03T16:00Z"
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T13:59Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness.ZERO_FITNESS))
    }

    @Test
    fun instantLaterThanEndOfIntervalScoresZeroForOptionalParticipant() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                importance = 0,
                                intervals = "2017-10-03T14:15Z/2017-10-03T16:00Z"
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T16:01Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness.ZERO_FITNESS))
    }

    @Test
    fun instantEarlierThanStartOfIntervalScoresNullForRequiredParticipant() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                importance = 100,
                                intervals = "2017-10-03T14:00Z/2017-10-03T16:00Z"
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T13:59Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness.NULL_FITNESS))
    }

    @Test
    fun instantLaterThanEndOfIntervalScoresNullForRequiredParticipant() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = participantWithAvailability(
                                importance = 100,
                                intervals = "2017-10-03T14:15Z/2017-10-03T16:00Z"
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T16:01Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness.NULL_FITNESS))
    }

    @Test
    fun instantForWhichAtLeastOneRequiredParticipantIsUnavailableScoresNull() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = *arrayOf(
                                participantWithAvailability(
                                        importance = 0,
                                        intervals = "2017-10-03T14:00Z/2017-10-03T17:00Z"
                                ),
                                participantWithAvailability(
                                        importance = 100,
                                        intervals = "2017-10-04T14:00Z/2017-10-04T17:00Z"
                                )
                        )
                )
        )
        val instant = Instant.parse("2017-10-03T15:00Z")
        assertThat(instantFitnessEvaluator.evaluate(instant))
                .isEqualTo(InstantFitness(instant, Fitness.NULL_FITNESS))
    }

    @Test
    fun instantForWhichAllRequiredParticipantsAndNoneOfOptionalArePresentScoresPositive() {
        val instantFitnessEvaluator = InstantFitnessEvaluator(
                meetingWithParticipants(
                        participants = *arrayOf(
                                participantWithAvailability(
                                        importance = 0,
                                        intervals = "2017-10-03T14:00Z/2017-10-03T17:00Z"
                                ),
                                participantWithAvailability(
                                        importance = 100,
                                        intervals = "2017-10-04T14:00Z/2017-10-04T17:00Z"
                                )
                        )
                )
        )
        val instant = Instant.parse("2017-10-04T15:00Z")
        assertThat(instantFitnessEvaluator.evaluate(instant).fitness.value).isGreaterThan(0)
    }

}