package com.scherule.scheduling

import com.scherule.scheduling.algorithms.SchedulingAlgorithm

class SchedulersLibrary(
        private val algorithms: Map<String, SchedulingAlgorithm>
) {

    fun getAlgorithm(algorithmType: String): SchedulingAlgorithm = algorithms.getOrElse(algorithmType, {
        throw IllegalArgumentException("The requested algorithm $algorithmType does not exist. " +
                "Available algorithms are ${algorithms.keys.joinToString(", ")}")
    })

}