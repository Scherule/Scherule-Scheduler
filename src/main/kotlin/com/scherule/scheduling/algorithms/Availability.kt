package com.scherule.scheduling.algorithms

import org.joda.time.Instant
import org.joda.time.Interval
import java.util.*
import java.util.stream.Collectors

class Availability(val intervals: Set<Interval>) {

    fun onlyWithin(between: Interval): Availability {
        return Availability(intervals.stream()
                .filter { between.overlaps(it) }
                .map { it.overlap(between) }
                .collect(Collectors.toSet()))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Availability

        if (intervals != other.intervals) return false

        return true
    }

    override fun hashCode(): Int {
        return intervals.hashCode()
    }

    override fun toString(): String {
        return "Availability(intervals=$intervals)"
    }

    fun getIntervalDuring(instant: Instant): Optional<Interval>
            = Optional.ofNullable(intervals.find {
        it.contains(instant)
    })

}