package guava

import com.google.common.util.concurrent.AtomicLongMap
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.StringGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.Test

@Param(name = "key", gen = StringGen::class, conf = "1:2")
class AtomicLongMapTest {
    private val map: AtomicLongMap<String> = AtomicLongMap.create()

    @Operation
    fun put(@Param(name = "key") key: String, value: Long) {
        map.put(key, value)
    }
    
    @Operation
    fun addAndGet(@Param(name = "key") key: String, value: Long): Long {
        return map.addAndGet(key, value)
    }

    @Operation
    fun get(@Param(name = "key") key: String): Long {
        return map[key]
    }

    @Operation
    fun remove(@Param(name = "key") key: String) {
        map.remove(key)
    }

    @Test
    fun ModelTest() {
        ModelCheckingOptions().check(AtomicLongMapTest::class)
    }
    
    @Test
    fun StressTest() {
        StressOptions().check(AtomicLongMapTest::class)
    }
}