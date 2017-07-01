package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Availability
import org.joda.time.Instant
import java.util.stream.Collectors
import java.util.stream.Stream

class AvailabilityByInstantMapper {

    fun mapByInstants(availabilityStream: Stream<Availability>): List<AvailabilitiesInInstant> {
        val availabilitiesByInstant = groupAvailabilitiesByInstant(availabilityStream)
        return availabilitiesByInstant.entries.stream()
                .map { (key, value) -> AvailabilitiesInInstant(key, value) }
                .sorted()
                .collect(Collectors.toList())
    }

    private fun groupAvailabilitiesByInstant(availabilityStream: Stream<Availability>): Map<Instant, Set<Availability>> {
        return availabilityStream
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

}