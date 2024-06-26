package fastutiltests

import it.unimi.dsi.fastutil.BigArrays
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.LongGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test
import java.util.concurrent.atomic.AtomicIntegerArray

@Param(name = "index", gen = LongGen::class, conf = "0:5")
class FastUtilBigArraysTest {
    private val array: Array<AtomicIntegerArray> = Array(5) { AtomicIntegerArray(5) }
    private val normal_array: Array<IntArray> = Array(5) { IntArray(5) }

    @Operation
    fun addAndGet(@Param(name = "index") index: Long, value: Int) {
        BigArrays.addAndGet(array, index, value)
    }

    @Operation
    fun getAndAdd(@Param(name = "index") index: Long, value: Int) {
        BigArrays.getAndAdd(array, index, value)
    }

    @Operation
    fun getAndSet(@Param(name = "index") index: Long, value: Int) {
        BigArrays.getAndSet(array, index, value)
    }

    @Operation
    fun getAndDecrement(@Param(name = "index") index: Long) {
        BigArrays.getAndDecrement(array, index)
    }

    @Operation
    fun getAndIncrement(@Param(name = "index") index: Long) {
        BigArrays.getAndIncrement(array, index)
    }

    @Operation
    fun decrementAndGet(@Param(name = "index") index: Long) {
        BigArrays.decrementAndGet(array, index)
    }

    @Operation
    fun incrementAndGet(@Param(name = "index") index: Long) {
        BigArrays.incrementAndGet(array, index)
    }

    @Operation
    fun compareAndSet(@Param(name = "index") index: Long, value: Int, expected: Int) {
        BigArrays.compareAndSet(array, index, expected, value)
    }

    @Operation
    fun add(@Param(name = "index") index: Long, value: Int) {
        BigArrays.add(normal_array, index, value)
    }

    @Operation
    fun decr(@Param(name = "index") index: Long) {
        BigArrays.decr(normal_array, index)
    }

    @Operation
    fun incr(@Param(name = "index") index: Long) {
        BigArrays.incr(normal_array, index)
    }

    @Operation
    fun mul(@Param(name = "index") index: Long, value: Int) {
        BigArrays.mul(normal_array, index, value)
    }

    @Test
    fun modelTest() {
        ModelCheckingOptions().check(FastUtilBigArraysTest::class)
    }

    @Test
    fun stressTest() {
        StressOptions().check(FastUtilBigArraysTest::class)
    }
}