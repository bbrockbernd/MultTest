package guava

import com.google.common.collect.ConcurrentHashMultiset
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.StringGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

@Param(name = "str", gen = StringGen::class, conf = "1:ab")
class ConcurrentHashMultisetTest {

    private val multiset = ConcurrentHashMultiset.create<String>()
    
    @Operation
    fun add(@Param(name = "str") str: String):Boolean {
        return multiset.add(str)
    }

    @Operation
    fun remove(@Param(name = "str") str: String):Boolean {
        return multiset.remove(str)
    }
    
    @Operation
    fun contains(@Param(name = "str") str: String): Boolean {
        return multiset.contains(str)
    }
    
    @Operation
    fun count(@Param(name = "str") str: String): Int {
        return multiset.count(str)
    }

    @Operation
    fun setCount(@Param(name = "str") str: String, count: Int): Int {
        return multiset.setCount(str, count)
    }

    @Operation
    fun setCount(@Param(name = "str") str: String, prevCount: Int, newCount: Int): Boolean {
        return multiset.setCount(str, prevCount, newCount)
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