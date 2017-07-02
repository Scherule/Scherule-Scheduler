package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Duration
import org.joda.time.Interval

class Availability(val weight: Int, val interval: Interval) {

    fun trimmedTo(between: Interval): Availability {

    }

    fun getDuration(): Duration = interval.toDuration()

}