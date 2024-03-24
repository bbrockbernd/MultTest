import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class DummyTest {
    private var value = 0
    
    @Operation
    fun inc() {
        value++
    }
    
    @Operation
    fun dec() {
        value--
    }

    @Operation
    fun get(): Int {
        return value
    }
    
    @Test
    fun Test() {
        ModelCheckingOptions().check(this::class)
    }
    
    
}