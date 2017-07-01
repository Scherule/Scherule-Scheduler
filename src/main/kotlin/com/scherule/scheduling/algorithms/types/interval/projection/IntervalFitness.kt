package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Interval

class IntervalFitness(val interval: Interval, val fitness: Fitness) {

    constructor(instantFitness: InstantFitness) : this(Interval(instantFitness.instant), instantFitness.fitness)

    fun expandTo(instantFitness: InstantFitness): IntervalFitness {
        val instant = instantFitness.instant
        val fitness = instantFitness.fitness

        val lowerFitness = fitness.thisOrIfLower(this.fitness)
        if (interval.isBefore(instant)) {
            return IntervalFitness(interval.withEnd(instant), lowerFitness)
        } else if (interval.isAfter(instant)) {
            return IntervalFitness(interval.withStart(instant), lowerFitness)
        } else {
            return IntervalFitness(interval, lowerFitness)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as IntervalFitness

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
        return "IntervalFitness(interval=$interval, fitness=$fitness)"
    }

}