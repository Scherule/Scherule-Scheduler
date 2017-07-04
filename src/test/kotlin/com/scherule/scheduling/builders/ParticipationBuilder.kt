package com.scherule.scheduling.builders

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.types.interval.projection.Availability


internal class ParticipationBuilder {

    val availabilities: MutableSet<Availability> = mutableSetOf()

    fun  withAvailability(vararg availability: Availability) = apply {
        availabilities.addAll(availability)
    }

    companion object {
        fun aParticipation() : ParticipationBuilder = ParticipationBuilder()
    }

    fun build(): Participation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}