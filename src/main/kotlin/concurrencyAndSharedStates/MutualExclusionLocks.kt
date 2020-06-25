package concurrencyAndSharedStates

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

/**
 * Mutual Exclusion locks.
 * We can create a mutex, so the Coroutines can look the variable while working on it, then unlock it when they are done.
 *
 * If another Coroutine needs to use the same value, it'll need to wait until its unlocked.
 *
 */
fun main() {
    runBlocking {
        val mutex = Mutex()
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock { counter++ }
            }
        }
        println("Counter = $counter")
    }
}

private suspend fun massiveRun(action: suspend () -> Int) {
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
