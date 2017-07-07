package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import org.joda.time.Duration
import org.joda.time.Interval

internal object ProblemUtils {

    fun problemWithParticipations(participations: Set<Participation>): SchedulingProblemPojo {
        return SchedulingProblemPojo(
                1,
                Duration.standardHours(2),
                Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                participations
        )
    }

    fun participationWithIntervals(vararg intervals: Interval): Participation {
        return Participation(
                participationId = "1",
                importance = 1,
                availabilities = intervals.map {
                    Availability(it)
                }.toSet()
        )
    }

}