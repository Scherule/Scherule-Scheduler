package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Instant

class InstantFitness(val instant: Instant, val fitness: Fitness) {
    fun isZero(): Boolean = fitness.isZero()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as InstantFitness

        if (instant != other.instant) return false
        if (fitness != other.fitness) return false

        return true
    }

    override fun hashCode(): Int {
        var result = instant.hashCode()
        result = 31 * result + fitness.hashCode()
        return result
    }

    override fun toString(): String {
        return "InstantFitness(instant=$instant, fitness=$fitness)"
    }

}