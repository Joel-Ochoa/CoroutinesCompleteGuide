package flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        sendNumbers3().collect() {
            println("Received $it")
        }
    }
}

private fun sendNumbers() = flow {
    for (i in 1..10) {
        emit(i)
    }
}

private fun sendNumbers2() = listOf(1, 2, 3).asFlow()

private fun sendNumbers3() = flowOf("One", "Two", "Three")