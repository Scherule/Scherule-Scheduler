package com.scherule.scheduling.builders

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.types.interval.projection.Availability


internal class ParticipationBuilder(
        private val participationId: String
) {

    var importance: Int = 0
    val availabilities: MutableSet<Availability> = mutableSetOf()

    fun withAvailability(vararg availability: Availability) = apply {
        availabilities.addAll(availability)
    }

    fun withImportance(importance: Int) = apply {
        this.importance = importance
    }

    companion object {
        fun aParticipation(participationId: String = "PARTICIPANT"):
                ParticipationBuilder = ParticipationBuilder(participationId)
    }

    fun build(): Participation = Participation(
            participationId = this.participationId,
            importance = this.importance,
            availabilities = this.availabilities
    )

}