package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


/**
 * Times goes from 4000ms to 3180 if we use buffer
 */
fun main() {
    runBlocking {
        val time = measureTimeMillis {
            generate()
                .buffer()
                .collect {
                    delay(300L)
                    println(it)
                }

        }
        println("Collected in $time ms")
    }
}

private fun generate() = flow {
    for (i in 1..10) {
        delay(100L)
        emit(i)
    }
}