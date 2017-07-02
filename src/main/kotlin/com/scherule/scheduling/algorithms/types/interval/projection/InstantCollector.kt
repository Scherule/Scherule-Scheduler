package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Instant

class InstantCollector(private val problem: SchedulingProblem) {

    fun getInstants(): List<Instant> {
        return problem.getParticipations()
                .flatMap { it.availabilities }
                .map { it.interval }
                .flatMap { listOf(Instant(it.start), Instant(it.end)) }
                .distinct()
                .sorted()
                .toList()
    }

}