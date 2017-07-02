package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Instant

class InstantFitnessEvaluator(private val problem: SchedulingProblem) {

    fun evaluate(instant: Instant): InstantFitness {
        val fitness = problem.getParticipations().stream()
                .map { evaluateForParticipant(it, instant) }
                .reduce(Fitness(1), {
                    firstFitness, secondFitness -> Fitness(firstFitness.value * secondFitness.value)
                })

        return InstantFitness(instant, fitness)
    }

    private fun evaluateForParticipant(participation: Participation, instant: Instant): Fitness {
        val matchingIntervalOptional = participation.availabilities.stream().filter { it.interval.contains(instant) }.findFirst()
        if(matchingIntervalOptional.isPresent) {
            val matchingInterval = matchingIntervalOptional.get()
            val validDuration = matchingInterval.trimmedTo(problem.getBetween()).getDuration()
            return Fitness(matchingInterval.weight * validDuration.standardHours.toInt())
        } else {
            return Fitness.ZERO_FITNESS
        }
    }

}