package channels

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Multiple coroutines send info to a channel at the same time.
 *
 * The channel passes the values as it gets them, this means first in, first out
 */
fun main() {
    runBlocking {
        val channel = Channel<String>()
        launch { sendString(channel, "Message1", 200L) }
        launch { sendString(channel, "Message2", 500L) }
        repeat(6) { println(channel.receive()) }
        coroutineContext.cancelChildren()
    }
}

suspend fun sendString(channel: SendChannel<String>, message: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(message)
    }
}