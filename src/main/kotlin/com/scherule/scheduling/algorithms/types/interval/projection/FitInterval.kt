package com.scherule.scheduling.algorithms.types.interval.projection

import org.joda.time.Instant
import org.joda.time.Interval

class FitInterval(val interval: Interval, val fitness: Fitness) {

    constructor(instant: Instant, fitness: Fitness) : this(Interval(instant), fitness)

    fun expandTo(instant: Instant, fitness: Fitness): FitInterval {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}