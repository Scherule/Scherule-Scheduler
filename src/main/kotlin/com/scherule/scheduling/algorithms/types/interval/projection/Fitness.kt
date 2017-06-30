package com.scherule.scheduling.algorithms.types.interval.projection

class Fitness(val value: Int) {

    fun isZero(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun thisOrIfLower(fitness: Fitness): Fitness = if(fitness.value < this.value) fitness else this

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

}