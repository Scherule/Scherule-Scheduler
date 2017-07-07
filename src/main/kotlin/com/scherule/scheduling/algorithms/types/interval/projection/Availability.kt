package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Duration
import org.joda.time.Interval

class Availability(val interval: Interval, val weight: Int = 1) {

    fun trimmedTo(between: Interval): Availability = Availability(interval.overlap(between), weight)

    fun getDuration(): Duration = interval.toDuration()

}