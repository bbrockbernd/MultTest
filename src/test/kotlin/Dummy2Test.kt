import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

class Dummy2Test {
    private var value = 0

//    @Operation
//    fun inc() {
//        value++
//    }
//
//    @Operation
//    fun dec() {
//        value--
//    }
    
    @Operation
    fun set(value: Int) {
        this.value = value
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
