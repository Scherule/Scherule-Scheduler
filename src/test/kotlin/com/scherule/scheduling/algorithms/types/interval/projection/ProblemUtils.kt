package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participant
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import org.joda.time.Duration
import org.joda.time.Interval

internal object ProblemUtils {

    fun meetingWithParticipants(
            minParticipants: Int = 1,
            durationInHours: Long = 1,
            vararg participants: Participant
    ): SchedulingProblemPojo {
        return SchedulingProblemPojo(
                minParticipants,
                Duration.standardHours(durationInHours),
                Interval.parse("1950-01-01T00:00Z/2050-12-31T23:59.59Z"),
                participants.toSet()
        )
    }

    fun participantWithAvailability(
            participationId: String = "optional",
            importance: Int = 0,
            vararg intervals: String
    ): Participant {
        return Participant(
                participantId = "1",
                importance = importance,
                availabilities = intervals.map {
                    Availability(Interval.parse(it))
                }.toSet()
        )
    }

}