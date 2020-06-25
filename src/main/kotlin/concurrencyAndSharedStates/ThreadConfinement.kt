package concurrencyAndSharedStates

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * there are two types of thread confinements.
 *
 * 1) Fine-grained: Each individual increment switches context which is much slower.
 *
 * 2) Coarse-grained: The whole function is run on a single thread no context switching, its faster but there are no
 * parallel coroutines working independently
 */
fun main() {
    runBlocking {
        val counterContext = newSingleThreadContext("CounterContext")
        var counter = 0
        //Option one: Fine -grained
        withContext(Dispatchers.Default) {
            massiveRun {
                //We do all the parallel work before switching to the singleThreadContext to update the variable
                withContext(counterContext) { counter++ }
            }
        }
        //Option two: Coarse - grained
        //We do all the work in the singleThreadContext. The threads wont run in parallel with this solution
        withContext(counterContext) {
            massiveRun {
                counter++
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
