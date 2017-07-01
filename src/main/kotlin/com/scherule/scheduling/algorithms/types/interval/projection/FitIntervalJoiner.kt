package com.scherule.scheduling.algorithms.types.interval.projection

import java.util.*

class FitIntervalJoiner {

    fun mapToFitIntervals(fitnessByInstant: List<InstantFitness>): HashSet<IntervalFitness> {
        return fitnessByInstant.fold(Stack<IntervalFitness>(), {
            accumulator, instantFitness ->
            if (instantFitness.isZero()) {
                accumulator.push(null)
            } else {
                if (accumulator.isEmpty()) {
                    accumulator.push(IntervalFitness(instantFitness))
                } else {
                    val existingInterval = accumulator.pop()
                    if (existingInterval == null) {
                        accumulator.push(IntervalFitness(instantFitness))
                    } else {
                        accumulator.push(existingInterval.expandTo(instantFitness))
                    }
                }
            }
            accumulator
        }).toCollection(HashSet())
    }

}