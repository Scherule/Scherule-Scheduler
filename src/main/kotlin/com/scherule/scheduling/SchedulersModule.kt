package com.scherule.scheduling

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.scherule.scheduling.algorithms.types.interval.projection.IntervalProjectionAlgorithm

class SchedulersModule : AbstractModule() {

    override fun configure() {
        bind(SchedulersLibrary::class.java)
                .annotatedWith(Names.named("scheduling.schedulers"))
                .toInstance(
                        SchedulersLibrary(
                                algorithms = mapOf(
                                        "interval-projection" to IntervalProjectionAlgorithm()
                                )
                        )
                )
    }

}
