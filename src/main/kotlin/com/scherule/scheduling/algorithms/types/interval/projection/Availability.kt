package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Duration
import org.joda.time.Interval

class Availability(val interval: Interval, val weight: Int = 1) {

    fun trimmedTo(between: Interval): Availability = Availability(interval.overlap(between), weight)

    fun getDuration(): Duration = interval.toDuration()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Availability

        if (!interval.isEqual(other.interval)) return false
        if (weight != other.weight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = interval.hashCode()
        result = 31 * result + weight
        return result
    }

    override fun toString(): String {
        return "Availability(interval=$interval, weight=$weight)"
    }

}