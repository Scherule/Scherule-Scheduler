package com.scherule.scheduling.algorithms.types.interval.projection

import java.util.*
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector

class IntervalFitnessCollector : Collector<InstantFitness, Stack<IntervalFitness>, List<IntervalFitness>> {

    override fun accumulator(): BiConsumer<Stack<IntervalFitness>, InstantFitness> {
        return BiConsumer<Stack<IntervalFitness>, InstantFitness> {
            accumulator, instantFitness ->
            if (instantFitness.isZero() || accumulator.isEmpty()) {
                accumulator.push(IntervalFitness(instantFitness))
            } else {
                val existingInterval = accumulator.pop()
                if (existingInterval.fitness.isZero()) {
                    accumulator.push(IntervalFitness(instantFitness))
                } else {
                    accumulator.push(existingInterval.expandTo(instantFitness))
                }
            }
        }
    }

    override fun combiner(): BinaryOperator<Stack<IntervalFitness>> {
        return BinaryOperator<Stack<IntervalFitness>> { t, u ->
            val combined = Stack<IntervalFitness>()
            combined.addAll(t)
            combined.addAll(u)
            combined
        }
    }

    override fun finisher(): Function<Stack<IntervalFitness>, List<IntervalFitness>> {
        return Function<Stack<IntervalFitness>, List<IntervalFitness>> {
            accumulator ->
            accumulator.filterNotNull().toList()
        }
    }

    override fun supplier(): Supplier<Stack<IntervalFitness>> {
        return Supplier<Stack<IntervalFitness>> {
            Stack<IntervalFitness>()
        }
    }

    override fun characteristics(): MutableSet<Collector.Characteristics> {
        return mutableSetOf()
    }

}