package concurrencyAndSharedStates

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun { counter++ }
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
