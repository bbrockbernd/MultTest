import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test
import java.util.concurrent.ConcurrentLinkedQueue

@Param(name = "value", gen = IntGen::class)
class MyConcurrentLinkedQueueTest {
    private val queue: MyConcurrentLinkedQueue<Int> = MyConcurrentLinkedQueue()

    @Operation
    fun add(@Param(name = "value") value: Int) {
        queue.add(value)
    }

    @Operation
    fun poll(): Int {
        return queue.poll()
    }

    @Operation
    fun peek(): Int {
        return queue.peek()
    }

    @Test
    fun Test() {
        ModelCheckingOptions().check(this::class)
    }
}

@Param(name = "value", gen = IntGen::class)
class ConcurrentLinkedQueueTest {
    private val queue: ConcurrentLinkedQueue<Int> = ConcurrentLinkedQueue()

    @Operation
    fun add(@Param(name = "value") value: Int) {
        queue.add(value)
    }

    @Operation
    fun poll(): Int {
        return queue.poll()
    }

    @Operation
    fun peek(): Int {
        return queue.peek()
    }

    @Test
    fun Test() {
        ModelCheckingOptions().check(this::class)
    }
}

