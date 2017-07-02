package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.SchedulingAlgorithm
import com.scherule.scheduling.algorithms.SchedulingProblem
import com.scherule.scheduling.algorithms.SchedulingSolution
import java.util.Comparator

class IntervalProjectionAlgorithm : SchedulingAlgorithm {

    override fun schedule(problem: SchedulingProblem): SchedulingSolution {
        val instantCollector = InstantCollector(problem)
        val instantEvaluator = InstantFitnessEvaluator(problem)

        val intervalWithHighestFitness = instantCollector.getInstants().stream().sorted()
                .map { instantEvaluator.evaluate(it) }
                .collect(IntervalFitnessCollector())
                .stream()
                .max(intervalComparator).get()

        return SchedulingSolution(intervalWithHighestFitness.interval)
    }

}

object intervalComparator : Comparator<IntervalFitness> {

    override fun compare(firstInterval: IntervalFitness, secondInterval: IntervalFitness): Int {
        val firstFitness = firstInterval.fitness
        val secondFitness = secondInterval.fitness
        if (firstFitness.isMoreThan(secondFitness)) {
            return 1
        } else if(secondFitness.isMoreThan(firstFitness)) {
            return -1
        } else {
            return 0
        }
    }

}
