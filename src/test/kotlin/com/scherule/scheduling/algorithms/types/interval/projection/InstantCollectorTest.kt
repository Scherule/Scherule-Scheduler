package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.Participation
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.Instant
import org.joda.time.Interval
import org.junit.jupiter.api.Test
import java.util.stream.Stream

internal class InstantCollectorTest {

    val mapper = InstantCollector()

    @Test
    fun mapsSingleIntervalToTwoInstants() {
        val availability = Availability(100, Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"))
        val participation = Participation("1", 100, setOf(availability))
        assertThat(mapper.collectIntervalInstantsFrom(setOf(participation))).containsExactly(
                InstantAvailability(Instant.parse("2017-10-03T14:15Z"), availability, participation),
                InstantAvailability(Instant.parse("2017-10-03T16:00Z"), availability, participation)
        )
    }

    @Test
    fun mapsTwoNonOverlappingIntervalsToFourInstants() {
        val availability = Participation(
                setOf(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z"),
                        Interval.parse("2017-10-04T15:45Z/2017-10-04T17:35Z")
                )
        )
        assertThat(mapper.collectIntervalInstantsFrom(Stream.of(
                availability)
        )).containsExactly(
                InstantAvailability(Instant.parse("2017-10-03T14:15Z"), setOf(availability)),
                InstantAvailability(Instant.parse("2017-10-03T16:00Z"), setOf(availability)),
                InstantAvailability(Instant.parse("2017-10-04T15:45Z"), setOf(availability)),
                InstantAvailability(Instant.parse("2017-10-04T17:35Z"), setOf(availability))
        )
    }

    @Test
    fun mapsTwoOverlappingIntervalsToThreeInstants() {
        val firstAvailability = Participation(
                setOf(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T16:00Z")
                )
        )
        val secondAvailability = Participation(
                setOf(
                        Interval.parse("2017-10-03T14:15Z/2017-10-03T18:00Z")
                )
        )
        assertThat(mapper.collectIntervalInstantsFrom(
                Stream.of(
                        firstAvailability,
                        secondAvailability
                )
        )).containsExactly(
                InstantAvailability(Instant.parse("2017-10-03T14:15Z"), setOf(firstAvailability, secondAvailability)),
                InstantAvailability(Instant.parse("2017-10-03T16:00Z"), setOf(firstAvailability)),
                InstantAvailability(Instant.parse("2017-10-03T18:00Z"), setOf(secondAvailability))
        )
    }

}