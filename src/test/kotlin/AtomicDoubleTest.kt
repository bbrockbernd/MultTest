import com.google.common.util.concurrent.AtomicDouble
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class AtomicDoubleTest {
    private val double: AtomicDouble = AtomicDouble(0.0)
    
    @Operation
    fun addAndGet(delta: Double): Double {
        return double.addAndGet(delta)
    }
    
    @Operation
    fun getAndSet(newVal: Double): Double {
        return double.getAndSet(newVal)
    }
    
    @Operation
    fun set(newVal: Double) {
        double.set(newVal)
    }
    
    // TODO create manual double value for comparison
    @Operation
    fun compareAndSet(expect: Double, update: Double): Boolean {
        return double.compareAndSet(expect, update)
    }
    
    @Operation
    fun doubleValue(): Double {
        return double.toDouble()
    }
    
    @Operation
    fun floatValue(): Float {
        return double.toFloat()
    }
    
    @Operation
    fun get(): Double {
        return double.get()
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