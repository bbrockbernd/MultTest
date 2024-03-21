import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingCTest
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test
import org.multiverse.api.StmUtils
import org.multiverse.api.callables.TxnCallable
import org.multiverse.api.references.TxnInteger
import org.multiverse.api.references.TxnLong
import org.multiverse.stms.gamma.transactionalobjects.GammaTxnInteger

@ModelCheckingCTest
class BankAccountLincheckTest {

    private val nr: TxnInteger = GammaTxnInteger(0)

    @Operation
    fun increment() = StmUtils.atomic(TxnCallable { nr.increment() })

    @Operation
    fun decrement() = StmUtils.atomic(TxnCallable { nr.decrement() })

    @Operation
    fun getBalance(): Int = nr.atomicGet()

    @Test // JUnit
    fun stressTest() = StressOptions().check(this::class)
}
