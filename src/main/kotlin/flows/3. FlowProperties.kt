package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Flow Properties.
 *
 * 1. Flows are cold. That means that they only emit values once "collect" is called.
 *
 * 2. Flows cant be cancelled by themselves. They will be cancelled when the encompassing coroutine is cancelled
 */
fun main() {
    // Cold property example
    runBlocking {
        val numbersFlow = sendNumbers()
        println("Cold property example")
        println("Flow hasnt started yet")
        delay(1000)
        println("Starting flow now: ")
        sendNumbers().collect() { println(it) }
        print("\n\n")
        //or
        // numbersFlow.collect { println(it)}
    }

    //Cancellation example
    runBlocking {
        val numbersFlow = sendNumbers2()
        println("Cancellation property example: ")
        println("Starting flow: ")
        //Flow cancelled because coroutine is cancelled after one second, shouldn't print number 3
        withTimeoutOrNull(1000) {
            numbersFlow.collect { println(it) }
        }

    }
}

private fun sendNumbers() = listOf(1, 2, 3, 4).asFlow()

private fun sendNumbers2() = flow {
    val list = listOf(1, 2, 3)
    list.forEach {
        delay(400)
        emit(it)
    }
}