package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Instant

class InstantFitness(val instant: Instant, val fitness: Fitness) {
    fun isZero(): Boolean = fitness.isZero()
}