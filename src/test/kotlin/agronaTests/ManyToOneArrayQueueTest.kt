package agronaTests

import org.agrona.concurrent.ManyToOneConcurrentArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

// Test but fixing the non sequential poll with a busy wait
class ManyToOneArrayQueueTest {

    private val queue = ManyToOneConcurrentArrayQueue<Int>(3)

    @Operation(nonParallelGroup = "consumers")
    fun poll(): Int? {
        return queue.poll()
    }

    @Operation
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
