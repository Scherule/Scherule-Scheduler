package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Availability
import org.joda.time.Instant

class InstantFitnessEvaluator {

    fun evaluate(orderedInstants: LinkedHashMap<Instant, Availability>): LinkedHashMap<Instant, Fitness> {
        return LinkedHashMap<Instant, Fitness>()
    }

}