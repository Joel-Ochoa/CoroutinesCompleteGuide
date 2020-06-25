package channels

import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * A ticket channel is a channel that produces a unit after a given delay
 */
fun main() {
    runBlocking {
        val ticketChannel = ticker(100)
        launch {
            val startTime = System.currentTimeMillis()
            ticketChannel.consumeEach {
                val delta = System.currentTimeMillis() - startTime
                println("Received tick after $delta")
            }
        }
        delay(1000)
        println("Done!")
        ticketChannel.cancel()
    }
}