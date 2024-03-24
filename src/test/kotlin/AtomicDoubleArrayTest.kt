import com.google.common.util.concurrent.AtomicDoubleArray
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test

class AtomicDoubleArrayTest {
    private val array: AtomicDoubleArray = AtomicDoubleArray(10)

    @Operation
    fun set(index: Int, value: Double) {
        array[index] = value
    }

    @Operation
    fun get(index: Int): Double {
        return array[index]
    }

    @Operation
    fun addAndGet(index: Int, delta: Double): Double {
        return array.addAndGet(index, delta)
    }

    @Test
    fun Test() {
        ModelCheckingOptions().check(this::class)
    }
}