package com.scherule.scheduling.algorithms.types.interval.projection

import java.util.*
import java.util.function.Consumer
import java.util.stream.Stream
import java.util.stream.StreamSupport

class ConsecutiveSpliterator<T>(
        private val wrappedSpliterator: Spliterator<T>,
        private val n: Int
) : Spliterator<List<T>> {

    private val deque: Deque<T>

    private val dequeConsumer: Consumer<T>

    init {
        this.deque = ArrayDeque<T>()
        this.dequeConsumer = Consumer<T> { deque.addLast(it) }
    }

    override fun tryAdvance(action: Consumer<in List<T>>): Boolean {
        deque.pollFirst()
        fillDeque()
        if (deque.size == n) {
            val list = ArrayList(deque)
            action.accept(list)
            return true
        } else {
            return false
        }
    }

    private fun fillDeque() {
        while (deque.size < n && wrappedSpliterator.tryAdvance(dequeConsumer));
    }

    override fun trySplit(): Spliterator<List<T>>? {
        return null
    }

    override fun estimateSize(): Long {
        return wrappedSpliterator.estimateSize()
    }

    override fun characteristics(): Int {
        return wrappedSpliterator.characteristics().plus(Spliterator.ORDERED)
    }

}

object streamUtils {

    fun <E> consecutiveStream(stream: Stream<E>, n: Int): Stream<List<E>> {
        val spliterator = stream.spliterator()
        val wrapper = ConsecutiveSpliterator(spliterator, n)
        return StreamSupport.stream(wrapper, false)
    }

}