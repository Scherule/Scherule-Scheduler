package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.SchedulingProblem
import com.scherule.scheduling.algorithms.types.interval.projection.Fitness.Companion.ZERO_FITNESS
import org.joda.time.Instant

class InstantFitnessEvaluator(private val problem: SchedulingProblem) {

    fun evaluate(instant: Instant): InstantFitness {
        val fitness = problem.getParticipations().stream()
                .map { evaluateForParticipant(it, instant) }
                .reduce(Fitness.ZERO_FITNESS, {
                    firstFitness, secondFitness ->
                    when {
                        firstFitness.isValid() && secondFitness.isValid()
                        -> Fitness(firstFitness.value + secondFitness.value)
                        else -> Fitness.NULL_FITNESS
                    }
                })

        return InstantFitness(instant, fitness)
    }

    private fun evaluateForParticipant(participation: Participation, instant: Instant): Fitness {
        val matchingIntervalOptional = participation.availabilities.stream().filter {
            it.interval.end.isEqual(instant) || it.interval.contains(instant)
        }.findFirst()
        if (matchingIntervalOptional.isPresent) {
            val matchingInterval = matchingIntervalOptional.get()
            if (matchingInterval.interval.overlaps(problem.getBetween())) {
                val validDuration = matchingInterval.trimmedTo(problem.getBetween()).getDuration()
                return Fitness(
                        maxOf(participation.importance,1)
                                * matchingInterval.weight
                                * validDuration.standardHours.toInt()
                )
            }
        }

        when {
            participation.importance == 0 -> return ZERO_FITNESS
            else -> return Fitness.NULL_FITNESS
        }
    }

}