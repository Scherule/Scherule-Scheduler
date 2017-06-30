package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Instant
import org.joda.time.Interval

class FitInterval(val interval: Interval, val fitness: Fitness) {

    constructor(instant: Instant, fitness: Fitness) : this(Interval(instant), fitness)

    fun expandTo(instant: Instant, fitness: Fitness): FitInterval {
        val lowerFitness = fitness.thisOrIfLower(this.fitness)
        if (interval.isBefore(instant)) {
            return FitInterval(interval.withEnd(instant), lowerFitness)
        } else if (interval.isAfter(instant)) {
            return FitInterval(interval.withStart(instant), lowerFitness)
        } else {
            return FitInterval(interval, lowerFitness)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as FitInterval

        if (interval != other.interval) return false
        if (fitness != other.fitness) return false

        return true
    }

    override fun hashCode(): Int {
        var result = interval.hashCode()
        result = 31 * result + fitness.hashCode()
        return result
    }

    override fun toString(): String {
        return "FitInterval(interval=$interval, fitness=$fitness)"
    }

}