package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Instant
import java.util.stream.Collectors
import java.util.stream.Stream

class InstantCollector(private val problem: SchedulingProblem) {

    fun collectIntervalInstantsFrom(participation: Iterable<Participation>): List<Instant> {







        val availabilitiesByInstant = groupAvailabilitiesByInstant(participationStream)
        return availabilitiesByInstant.entries.stream()
                .map { (key, value) -> InstantAvailability(key, value) }
                .sorted()
                .collect(Collectors.toList())
    }

    private fun groupAvailabilitiesByInstant(participationStream: Stream<Participation>): Map<Instant, Set<Participation>> {
        return participationStream
                .flatMap {
                    val availability = it
                    it.intervals.stream().flatMap {
                        Stream.of(
                                Pair(it.start.toInstant(), availability),
                                Pair(it.end.toInstant(), availability)
                        )
                    }
                }.collect(Collectors.groupingBy({ it.first }, Collectors.mapping({ it.second }, Collectors.toSet())))
    }

    fun getInstants(): List<Instant> {

    }

}