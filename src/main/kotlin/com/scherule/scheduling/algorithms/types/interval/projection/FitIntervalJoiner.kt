package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Instant
import java.util.*

class FitIntervalJoiner {

    fun mapToFitIntervals(fitnessByInstant: LinkedHashMap<Instant, Fitness>): HashSet<FitInterval> {
        return fitnessByInstant.entries.fold(Stack<FitInterval>(), {
            accumulator, (instant, fitness) ->
            if (fitness.isZero()) {
                accumulator.push(null)
            } else {
                if (accumulator.isEmpty()) {
                    accumulator.push(FitInterval(instant, fitness))
                } else {
                    val existingInterval = accumulator.pop()
                    if (existingInterval == null) {
                        accumulator.push(FitInterval(instant, fitness))
                    } else {
                        accumulator.push(existingInterval.expandTo(instant, fitness))
                    }
                }
            }
            accumulator
        }).toCollection(HashSet())
    }

}