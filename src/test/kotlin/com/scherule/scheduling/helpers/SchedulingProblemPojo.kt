package com.scherule.scheduling.helpers

import com.scherule.scheduling.algorithms.Participant
import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Duration
import org.joda.time.Interval

internal class SchedulingProblemPojo(
        private val minParticipants: Int,
        private val minDuration: Duration,
        private val between: Interval,
        private val participant: Set<Participant>
) : SchedulingProblem {

    override fun getParticipants(): Set<Participant> {
        return participant
    }

    override fun getMinParticipants(): Int {
        return minParticipants
    }

    override fun getMinDuration(): Duration {
        return minDuration
    }

    override fun getBetween(): Interval {
        return between
    }

    companion object {
        fun aSchedulingProblem() = Builder()
    }

    private constructor(builder: Builder) : this(
            minParticipants = builder.minParticipants,
            minDuration = builder.minDuration,
            between = builder.between,
            participant = builder.participant
    )

    class Builder {
        var minParticipants = 1
            private set
        var minDuration = Duration.standardHours(1)
            private set
        var between = Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z")
            private set
        var participant: MutableSet<Participant> = mutableSetOf()
            private set

        fun withMinParticipants(minParticipants: Int) = apply { this.minParticipants = minParticipants }

        fun longerThan(minDuration: Duration) = apply { this.minDuration = minDuration }

        fun inBetween(between: Interval) = apply { this.between = between }

        fun withParticipation(vararg participant: Participant) = apply { this.participant.addAll(participant) }

        fun build() = SchedulingProblemPojo(this)

    }

}