package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Availability
import com.scherule.scheduling.algorithms.types.interval.projection.Fitness.Companion.ZERO_FITNESS
import org.joda.time.Instant

class InstantFitnessEvaluator {

    fun evaluate(availabilitiesInInstant: AvailabilitiesInInstant): InstantFitness {
        val instant = availabilitiesInInstant.instant
        val availabilities = availabilitiesInInstant.availabilities
        val joinedFitness = availabilities.map { score(instant, it) }.reduce {
            first, second ->
            first.combineWith(second)
        }
        return InstantFitness(instant, joinedFitness)
    }

    private fun score(instant: Instant, availability: Availability) : Fitness {
        val optionalIntervalDuring = availability.getIntervalDuring(instant)
        if (optionalIntervalDuring.isPresent) {
            val interval = optionalIntervalDuring.get()
            return Fitness(interval.toDuration().standardMinutes.toInt())
        } else {
            return ZERO_FITNESS
        }
    }

}