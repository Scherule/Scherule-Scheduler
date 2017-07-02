package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import org.joda.time.Instant

class InstantAvailability(
        val instant: Instant,
        val availability: Availability,
        val participation: Participation
) : Comparable<InstantAvailability> {

    override fun compareTo(other: InstantAvailability): Int {
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