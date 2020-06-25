package channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A buffered channel is a channel with a limited capacity
 * When the capacity is reached, the sender is paused.
 *
 */

//Will send 4 values, then will wait until one or more is received to send another one.
// In this example we are sending four values, then receiving two values so it have 2 free spots to send another two
fun main() {
    runBlocking {
        val channel = Channel<Int>(4)
        val sender = launch {
            repeat(10) {
                channel.send(it)
                println("Sent $it")
            }
        }

        repeat(3) {
            delay(1000)
            println("Received ${channel.receive()}")
            println("Received ${channel.receive()}")
        }
        sender.cancel()
    }
}
