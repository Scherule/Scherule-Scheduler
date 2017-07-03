package com.scherule.scheduling.algorithms

import org.joda.time.Interval


class SchedulingSolution(val interval: Interval) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as SchedulingSolution

        if (!interval.isEqual(other.interval)) return false

        return true
    }

    override fun hashCode(): Int {
        return interval.hashCode()
    }

    override fun toString(): String {
        return "SchedulingSolution(interval=$interval)"
    }

}