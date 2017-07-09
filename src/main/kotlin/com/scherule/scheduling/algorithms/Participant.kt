package com.scherule.scheduling.algorithms

import com.scherule.scheduling.algorithms.types.interval.projection.Availability

class Participant(
        val participantId: String,
        val importance: Int,
        val availabilities: Set<Availability>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Participant

        if (participantId != other.participantId) return false
        if (importance != other.importance) return false
        if (availabilities != other.availabilities) return false

        return true
    }

    override fun hashCode(): Int {
        var result = participantId.hashCode()
        result = 31 * result + importance
        result = 31 * result + availabilities.hashCode()
        return result
    }

}