package com.scherule.scheduling.algorithms

import org.joda.time.Duration
import org.joda.time.Interval


interface SchedulingProblem {

    fun getMinParticipants() : Int
    fun getMinDuration() : Duration
    fun  getBetween(): Interval

}