package com.scherule.scheduling

import com.scherule.scheduling.algorithms.Participant
import com.scherule.scheduling.algorithms.SchedulingProblem
import com.scherule.scheduling.algorithms.types.interval.projection.Availability
import sun.jvm.hotspot.utilities.Interval
import java.time.Duration

class SchedulingJob(jsonValue: String) : SchedulingProblem {

    val context: DocumentContext = JsonPath.parse(jsonValue);

    override fun getMinParticipants(): Int {
        return context.read("$.parameters.minParticipants")
    }

    override fun getMinDuration(): Duration {
        return Duration.millis(context.read("$.parameters.minDuration"))
    }

    override fun getBetween(): Interval {
        val between: String = context.read("$.parameters.between")
        val betweenParts = between.split("-").map { it.toLong() }
        return Interval(betweenParts[0], betweenParts[1])
    }

    override fun getParticipants(): Set<Participant> {
        val participantIds: List<String> = context.read("$.participants[*].id.id")
        return participantIds.map {
            val participantDetailsArray: JSONArray = context.read("$.participants[?(@.id.id == '$it')]")
            val participantDetails = participantDetailsArray[0] as Map<*, *>
            val availabilitiesDetailsArray: JSONArray = participantDetails["availability"] as JSONArray
            Participant(
                    participantId = it,
                    importance = participantDetails.get("importance") as Int,
                    availabilities = availabilitiesDetailsArray.map {
                        val participationDetails = it as Map<*, *>
                        val interval = participationDetails["interval"] as String
                        val weight = participationDetails["preference"] as Int
                        val intervals = interval.split("-").map { it.toLong() }
                        Availability(
                                Interval(intervals[0], intervals[1]),
                                weight
                        )
                    }.toSet()
            )
        }.toSet()
    }

    fun getAlgorithmType(): String = context.read("$.algorithm.type")

}