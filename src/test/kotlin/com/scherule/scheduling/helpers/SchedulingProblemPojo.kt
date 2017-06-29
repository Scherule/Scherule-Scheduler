package com.scherule.scheduling.helpers

import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Duration
import org.joda.time.Interval

internal class SchedulingProblemPojo(
        private val minParticipants: Int,
        private val minDuration: Duration,
        private val between: Interval
) : SchedulingProblem {

    override fun getMinParticipants(): Int {
        return minParticipants
    }

    override fun getMinDuration(): Duration {
        return minDuration
    }

    override fun getBetween(): Interval {
        return between
    }


}