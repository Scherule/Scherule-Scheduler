package com.scherule.scheduling.algorithms

import com.google.inject.AbstractModule
import com.google.inject.name.Names.named
import com.scherule.scheduling.algorithms.types.interval.projection.IntervalProjectionAlgorithm

class AlgorithmsModule : AbstractModule() {

    override fun configure() {
        bind(Map::class.java).annotatedWith(named("scheduling.channel")).toInstance(mapOf(
                Pair("interval-projection", IntervalProjectionAlgorithm())
        ))
    }

}
