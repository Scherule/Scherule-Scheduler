package com.scherule.scheduling.algorithms

import com.google.inject.AbstractModule
import com.google.inject.multibindings.MapBinder
import com.scherule.scheduling.algorithms.types.interval.projection.IntervalProjectionAlgorithm

class AlgorithmsModule : AbstractModule() {

    override fun configure() {
        val mapBinder = MapBinder.newMapBinder(binder(), String::class.java, SchedulingAlgorithm::class.java)
        mapBinder.addBinding("interval-projection").toInstance(IntervalProjectionAlgorithm())
    }

}
