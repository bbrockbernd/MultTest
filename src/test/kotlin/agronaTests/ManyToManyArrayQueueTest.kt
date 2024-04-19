package agronaTests

import org.agrona.concurrent.ManyToOneConcurrentArrayQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class ManyToManyArrayQueueTest {

    private val queue = ManyToOneConcurrentArrayQueue<Int>(3)

    @Operation
    fun poll(): Int? {
        return queue.poll()
    }

    @Operation
    fun size(): Int {
        return queue.size
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