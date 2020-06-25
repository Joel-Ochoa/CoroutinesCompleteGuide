package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        //        zip()
        combine()
    }

}

private suspend fun zip() {
    val english = flowOf("one", "two", "three")
    val french = flowOf("un", "deux", "troix")

    english.zip(french) { a, b -> "'$a' in french is '$b'" }
        .collect {
            println(it)
        }
}

/**
 * shows
 * 1 is one
2 is one
2 is two
3 is two
3 is three
4 is three
5 is three
5 is four
5 is five
Because of the difference in the delay
 */
private suspend fun combine() {
    val numbers = (1..5).asFlow().onEach { delay(300L) }
    val values = flowOf("one", "two", "three", "four", "five")
        .onEach { delay(400L) }
    numbers.combine(values) { a, b ->
        "$a is $b"
    }
        .collect { println(it) }
}