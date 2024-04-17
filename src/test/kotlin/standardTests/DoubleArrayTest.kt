package standardTests

import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

@Param(name = "index", gen = IntGen::class, conf = "1:2")
class DoubleArrayTest {
    private val array: DoubleArray = DoubleArray(3)

    @Operation
    fun set(@Param(name = "index") index: Int, value: Double) {
        array[index] = value
    }

    @Operation
    fun get(@Param(name = "index") index: Int): Double {
        return array[index]
    }

    @Operation
    fun size(): Int {
        return array.size
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
