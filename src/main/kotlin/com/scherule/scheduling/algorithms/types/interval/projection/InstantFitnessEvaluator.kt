package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Instant

class InstantFitnessEvaluator(private val problem: SchedulingProblem) {

    fun evaluate(instant: Instant): InstantFitness {
        val instant = instantAvailability.instant
        val availability = instantAvailability.availability
        return InstantFitness(instant, Fitness(availability.getDuration().standardMinutes.toInt()))
    }

}