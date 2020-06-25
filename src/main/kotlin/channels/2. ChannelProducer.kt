package channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {

        //option 1
        val channel = produce {
            for (x in 1..5) {
                send(x * x)
            }
        }
        for (y in channel) {
            println(y)
        }

        //option 2. Using extension function
        for (y in produceSquares()) {
            println(y)
        }

        //option 3. Using extension function and 'consumeEach' instead of for loop
        produceSquares().consumeEach { println(it) }
    }
}

fun CoroutineScope.produceSquares() = produce {
    for (x in 1..5)
        send(x * x)
}