package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Availability
import com.scherule.scheduling.algorithms.types.interval.projection.Fitness.Companion.ZERO_FITNESS
import org.joda.time.Instant

class InstantFitnessEvaluator {

    fun evaluate(orderedInstants: List<AvailabilitiesInInstant>): List<InstantFitness> {
        return orderedInstants.map {
            val instant = it.instant
            val joinedFitness = it.availabilities.map { score(instant, it) }.reduce {
                first, second ->
                first.combineWith(second)
            }
            InstantFitness(instant, joinedFitness)
        }
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