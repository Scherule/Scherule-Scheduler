package com.scherule.scheduling.algorithms.types.interval.projection

class Fitness(val value: Int) {

    companion object {
        val ZERO_FITNESS = Fitness(0)
        val NULL_FITNESS = Fitness(-1)
    }

    fun isZero(): Boolean {
        return ZERO_FITNESS == this
    }

    fun thisOrIfLower(fitness: Fitness): Fitness = if (fitness.value < this.value) fitness else this

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Fitness

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return "Fitness(value=$value)"
    }

    fun isMoreThan(secondFitness: Fitness): Boolean = this.value > secondFitness.value
    fun isValid(): Boolean = this.value >= 0

}