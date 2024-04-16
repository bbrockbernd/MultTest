package org.example

import MyConcurrentLinkedQueue
import org.multiverse.api.StmUtils
import org.multiverse.api.callables.TxnCallable
import org.multiverse.api.references.TxnInteger
import org.multiverse.stms.gamma.transactionalobjects.GammaTxnInteger

fun main() {
    
    val queue: MyConcurrentLinkedQueue<Int> = MyConcurrentLinkedQueue()
    val nr: TxnInteger = GammaTxnInteger(0)
    val nr2: TxnInteger = GammaTxnInteger(0)

    fun increment() = StmUtils.atomic(TxnCallable { nr.increment() })

    fun decrement() = StmUtils.atomic(TxnCallable { nr.decrement() })
    fun getBalance(): Int = nr.atomicGet()
    
    increment()
    increment()
    
    println(getBalance())
    println(nr2.atomicGet())
    
    
}