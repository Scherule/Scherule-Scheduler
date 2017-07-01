package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Availability
import org.joda.time.Instant

class AvailabilitiesInInstant(
        val instant: Instant,
        val availabilities: Set<Availability>
) : Comparable<AvailabilitiesInInstant> {

    override fun compareTo(other: AvailabilitiesInInstant): Int {
        val thisInstant = instant
        val otherInstant = other.instant
        if (thisInstant.isAfter(otherInstant)) {
            return 1
        } else if (thisInstant.isBefore(otherInstant)) {
            return -1
        } else {
            return 0
        }
    }

    fun evaluateBy(intervalFitnessEvaluator: InstantFitnessEvaluator): InstantFitness {
        return intervalFitnessEvaluator.evaluate(this)
    }

}