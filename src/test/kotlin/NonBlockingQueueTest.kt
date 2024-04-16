import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.annotations.Param
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.paramgen.IntGen
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.Test
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.Volatile


private class Node<T>(
    @Volatile var value: T,
    @Volatile var next: Node<T>? = null,
    @Volatile var previous: Node<T>? = null
) {
}


class NonBlockingQueue<T> {
    private val head: AtomicReference<Node<T>?> = AtomicReference(null)
    private val tail: AtomicReference<Node<T>?> = AtomicReference(null)
    private val size = AtomicInteger()

    init {
        size.set(0)
    }

    fun add(element: T?) {
        if (element == null) {
            throw NullPointerException()
        }

        val node = Node<T>(element)
        var currentTail: Node<T>?
        do {
            currentTail = tail.get()
            node.previous = currentTail
        } while (!tail.compareAndSet(currentTail, node))

        if (node.previous != null) {
            node.previous!!.next = node
        }

        head.compareAndSet(null, node) // for inserting the first element
        size.incrementAndGet()
    }

    fun get(): T {
        if (head.get() == null) {
            throw NoSuchElementException()
        }

        var currentHead: Node<T>
        var nextNode: Node<T>?
        do {
            currentHead = head.get()!!
            nextNode = currentHead.next
        } while (!head.compareAndSet(currentHead, nextNode))

        size.decrementAndGet()
        return currentHead.value
    }
}

@Param(name = "value", gen = IntGen::class, conf = "1:2")
class ConcurrentLinkedQueueTest {
    private val queue: MyConcurrentLinkedQueue<Int> = MyConcurrentLinkedQueue()

    @Operation
    fun add(@Param(name = "value") value: Int) {
        queue.add(value)
    }

    @Operation
    fun poll(): Int {
        return queue.poll()
    }

    @Operation
    fun peek(): Int {
        return queue.peek()
    }

    @Test
    fun Test() {
        ModelCheckingOptions().check(this::class)
    }
}

@Param(name = "value", gen = IntGen::class, conf = "1:2")
class NonBlockingQueueTest {
    private val queue: NonBlockingQueue<Int> = NonBlockingQueue()

    @Operation
    fun add(@Param(name = "value") value: Int) {
        queue.add(value)
    }

    @Operation
    fun get(): Int {
        return queue.get()
    }

    @Test
    fun Test() {
        ModelCheckingOptions().check(this::class)
    }
}

