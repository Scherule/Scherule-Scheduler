package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Availability
import org.joda.time.Instant
import java.util.stream.Stream

class AvailabilityByInstantMapper {

    fun mapByInstants(availabilityStream: Stream<Availability>): LinkedHashMap<Instant, Set<Availability>> {
        return availabilityStream
                .flatMap {
                    val availability = it
                    it.intervals.stream().flatMap {
                        Stream.of(Pair(it.start.toInstant(), availability), Pair(it.end.toInstant(), availability))
                    }
                }.sorted { a, b -> a.first.compareTo(b.first) }
                .collect({ LinkedHashMap() },
                        { map, (first, second) -> map.put(first, map.getOrDefault(first, HashSet()).plus(second)) },
                        { map, carryMap -> map.putAll(carryMap) })
    }

}