import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class DoubleArrayEasyTest {
    
    private val doubleArray: DoubleArray = DoubleArray(1)
    private var myDouble: Double = 0.0
    private var myInt: Int = 0
    
    @Operation
//    fun set(double: Double) { this.doubleArray[0] = double }
//    fun set(double: Double) { myDouble = double }
    fun set(i: Int) { myInt = i }
    
    @Operation
//    fun get(): Double = doubleArray[0]
//    fun get(): Double = myDouble
    fun get(): Int = myInt
    
    @Test
    fun stressTest() {
        StressOptions().check(this::class)
    }
    
    @Test
    fun modelTest() {
        ModelCheckingOptions().check(this::class)
    }
}