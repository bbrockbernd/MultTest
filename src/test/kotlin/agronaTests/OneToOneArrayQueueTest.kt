package agronaTests

import org.agrona.concurrent.OneToOneConcurrentArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class OneToOneArrayQueueTest {
    
    private val queue = OneToOneConcurrentArrayQueue<Int>(3)
    
    @Operation(nonParallelGroup = "consumers")
    fun poll(): Int? {
        return queue.poll()
    }

    @Operation(nonParallelGroup = "consumers")
    fun drain(): String {
        val list = mutableListOf<Int>()
        val nEls = queue.drain { el: Int -> list.add(el) }
        return "${list.toIntArray().contentToString()} n = $nEls"
    }

    @Operation(nonParallelGroup = "producers")
    fun offer(int: Int): Boolean {
        return queue.offer(int)
    }

    @Test
    fun modelTest() {
        ModelCheckingOptions().check(this::class)
    }

    @Test
    fun stressTest() {
        StressOptions().check(this::class)
    }
    
}