package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.SchedulingAlgorithm
import com.scherule.scheduling.algorithms.SchedulingProblem
import com.scherule.scheduling.algorithms.SchedulingSolution
import org.joda.time.Duration
import java.util.*
import java.util.stream.Stream

class IntervalProjectionAlgorithm : SchedulingAlgorithm {

    override fun schedule(problem: SchedulingProblem): SchedulingSolution {
        val instantCollector = InstantCollector(problem)
        val instantEvaluator = InstantFitnessEvaluator(problem)

        val intervalWithHighestFitness = streamUtils.consecutiveStream(instantCollector.getInstants()
                .stream(), 2)
                .flatMap {
                    Stream.of(
                            it[0],
                            it[0].plus(Duration(it[0], it[1]).dividedBy(2)).toInstant(),
                            it[1]
                    )
                }
                .map { instantEvaluator.evaluate(it) }
                .collect(IntervalFitnessCollector())
                .stream()
                .max(intervalComparator)
                .get()

        return SchedulingSolution(intervalWithHighestFitness.interval)
    }


    object intervalComparator : Comparator<IntervalFitness> {

        override fun compare(firstInterval: IntervalFitness, secondInterval: IntervalFitness): Int {
            val firstFitness = firstInterval.fitness
            val secondFitness = secondInterval.fitness
            if (firstFitness.isMoreThan(secondFitness)) {
                return 1
            } else if (secondFitness.isMoreThan(firstFitness)) {
                return -1
            } else {
                return 0
            }
        }

    }

}