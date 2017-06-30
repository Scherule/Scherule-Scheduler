package com.scherule.scheduling.algorithms.types.interval.projection

import com.scherule.scheduling.algorithms.SchedulingAlgorithm
import com.scherule.scheduling.algorithms.SchedulingProblem
import com.scherule.scheduling.algorithms.SchedulingSolution

/**
 * A simple algorithm that creates a series of time intervals out of the longer intervals.
 */
class IntervalProjectionAlgorithm : SchedulingAlgorithm {

    val availabilityByInstantMapper = AvailabilityByInstantMapper()
    val intervalFitnessEvaluator = InstantFitnessEvaluator()
    val fitIntervalJoiner = FitIntervalJoiner()

    override fun schedule(problem: SchedulingProblem): SchedulingSolution {
        val orderedInstants = availabilityByInstantMapper.mapByInstants(getAvailabilityStream(problem))
        val fitnessByInstant = intervalFitnessEvaluator.evaluate(orderedInstants)
        val fitnessByInterval = fitIntervalJoiner.mapToFitIntervals(fitnessByInstant)
        return pickBestFrom(fitnessByInterval)
    }

    private fun pickBestFrom(intervals: HashSet<FitInterval>): SchedulingSolution {
        val bestInterval = intervals.maxBy { it.fitness.value }!!
        return SchedulingSolution(bestInterval.interval)
    }

    private fun getAvailabilityStream(problem: SchedulingProblem) =
            problem.getAvailabilityByParticipants().stream().map {
                it.onlyWithin(problem.getBetween())
            }

}