package com.scherule.scheduling.algorithms

interface SchedulingAlgorithm {

    fun schedule(problem: SchedulingProblem) : SchedulingSolution

}