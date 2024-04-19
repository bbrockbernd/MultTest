import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingCTest
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test
import org.multiverse.api.StmUtils
import org.multiverse.api.callables.TxnCallable
import org.multiverse.api.references.TxnInteger
import org.multiverse.stms.gamma.transactionalobjects.GammaTxnInteger

class MultiverseTest {

    public lateinit var nr: TxnInteger

    @Operation
    fun increment() = StmUtils.atomic(TxnCallable { nr.increment() })

    @Operation
    fun decrement() = StmUtils.atomic(TxnCallable { nr.decrement() })

    @Operation
    fun getBalance(): Int = nr.atomicGet()

    @Test // JUnit
    fun stressTest() {
            nr = GammaTxnInteger(0)
        ModelCheckingOptions().check(this::class)
        }
}
