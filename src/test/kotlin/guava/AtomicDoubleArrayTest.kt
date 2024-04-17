package guava

import com.google.common.util.concurrent.AtomicDoubleArray
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

@Param(name = "index", gen = IntGen::class, conf = "1:2")
class AtomicDoubleArrayTest {
    private val array: AtomicDoubleArray = AtomicDoubleArray(3)

    @Operation
    fun set(@Param(name = "index") index: Int, value: Double) {
        array[index] = value
    }

    @Operation
    fun get(@Param(name = "index") index: Int): Double {
        return array[index]
    }

    @Operation
    fun addAndGet(@Param(name = "index") index: Int, delta: Double): Double {
        return array.addAndGet(index, delta)
    }
    @Operation
    fun length(): Int {
        return array.length()
    }

    @Test
    fun modelTest() {
        ModelCheckingOptions().check(AtomicDoubleArrayTest::class)
    }

    @Test
    fun stressTest() {
        StressOptions().check(AtomicDoubleArrayTest::class)
    }
}