package multiverseTests

import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test
import org.multiverse.api.references.TxnRef
import org.multiverse.stms.gamma.transactionalobjects.GammaTxnRef

class TxnRefTest {
    private lateinit var ref: TxnRef<Int>
    
    @Operation
    fun set(value: Int) {
        ref.set(value)
    }
    
    @Operation
    fun get(): Int {
        return ref.get()
    }
    
    @Test
    fun stressTest() {
        ref = GammaTxnRef<Int>(0)
        StressOptions().check(this::class)
    }
    
    @Test
    fun modelTest() {
        ref = GammaTxnRef<Int>(0)
        ModelCheckingOptions().check(this::class)
    }
    
    
}