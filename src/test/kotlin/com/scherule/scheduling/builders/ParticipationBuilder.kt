package com.scherule.scheduling.builders

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.types.interval.projection.Availability


internal class ParticipationBuilder {

    var importance: Int = 1
    var participationId: String = "1"
    val availabilities: MutableSet<Availability> = mutableSetOf()

    fun withAvailability(vararg availability: Availability) = apply {
        availabilities.addAll(availability)
    }

    companion object {
        fun aParticipation(): ParticipationBuilder = ParticipationBuilder()
    }

    fun build(): Participation = Participation(
            participationId = this.participationId,
            importance = this.importance,
            availabilities = this.availabilities
    )

}