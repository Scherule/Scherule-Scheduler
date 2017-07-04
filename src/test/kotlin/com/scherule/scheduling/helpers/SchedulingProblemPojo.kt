package com.scherule.scheduling.helpers

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.Interval

internal class SchedulingProblemPojo(
        private val minParticipants: Int,
        private val minDuration: Duration,
        private val between: Interval,
        private val participation: Set<Participation>
) : SchedulingProblem {

    override fun getParticipations(): Set<Participation> {
        return participation
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
            participation = builder.participation
    )

    class Builder {
        var minParticipants = 1
            private set
        var minDuration = Duration.ZERO
            private set
        var between = Interval.parse("2017-10-02T14:15Z/2017-10-05T16:00Z")
            private set
        var participation: MutableSet<Participation> = mutableSetOf()
            private set

        fun withMinParticipants(minParticipants: Int) = apply { this.minParticipants = minParticipants }

        fun longerThan(minDuration: Duration) = apply { this.minDuration = minDuration }

        fun inBetween(between: Interval) = apply { this.between = between }

        fun withParticipation(vararg participation: Participation) = apply { this.participation.addAll(participation) }

        fun build() = SchedulingProblemPojo(this)

    }

}