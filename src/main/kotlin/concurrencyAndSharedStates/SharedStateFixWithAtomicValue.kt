package concurrencyAndSharedStates

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

/**
 * Fix with AtomicValue.
 *
 * An atomic value is a value that can only be modified by one thread at the time, if two or more thread want to update it
 * at the same time, they'll have to wait until their turn.
 *
 * - This solution works for all primitive data types and collections.
 * - This solution doesn't work for complex data types that don't have a particular thread implementation.
 */
fun main() {
    runBlocking {
        var counter = AtomicInteger()
        withContext(Dispatchers.Default) {
            massiveRunAtomic { counter.incrementAndGet() }
        }
        println("Counter = $counter")
    }
}

private suspend fun massiveRunAtomic(action: suspend () -> Int) {
    val n = 100
    val k = 1000

    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")

}
