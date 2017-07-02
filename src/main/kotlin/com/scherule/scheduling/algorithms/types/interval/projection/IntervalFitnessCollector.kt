package com.scherule.scheduling.algorithms.types.interval.projection

import java.util.*
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Stream

class IntervalFitnessCollector : Collector<InstantFitness, MutableList<IntervalFitness>, List<IntervalFitness>> {

    override fun accumulator(): BiConsumer<MutableList<IntervalFitness>, InstantFitness> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun characteristics(): MutableSet<Collector.Characteristics> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun combiner(): BinaryOperator<MutableList<IntervalFitness>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finisher(): Function<MutableList<IntervalFitness>, List<IntervalFitness>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun supplier(): Supplier<MutableList<IntervalFitness>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun mapToFitIntervals(fitnessByInstant: List<InstantFitness>): HashSet<IntervalFitness> {
        return fitnessByInstant.fold(Stack<IntervalFitness>(), {
            accumulator, instantFitness ->
            if (instantFitness.isZero()) {
                accumulator.push(null)
            } else {
                if (accumulator.isEmpty()) {
                    accumulator.push(IntervalFitness(instantFitness))
                } else {
                    val existingInterval = accumulator.pop()
                    if (existingInterval == null) {
                        accumulator.push(IntervalFitness(instantFitness))
                    } else {
                        accumulator.push(existingInterval.expandTo(instantFitness))
                    }
                }
            }
            accumulator
        }).toCollection(HashSet())
    }

}