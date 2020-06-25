package flows

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() {
    runBlocking {
        tryCatch()
    }
}

suspend fun tryCatch() {
    try {
        (1..3).asFlow()
            .onEach { check(it != 2) }
            .collect() { println(it) }
    } catch (e: Exception) {
        println("Caught exception $e")
    }
}

suspend fun catch() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .catch { e -> println("Caught exception $e") }
        .collect() { println(it) }
}

/**
 * flows.onCompletion works like "finally"
 */
suspend fun onCompletion() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .catch { e -> println("Caught exception $e") }
        .onCompletion { e -> if (e != null) println("Flow completed with exception $e") else println("Flow completed") }
        .collect() { println(it) }
}