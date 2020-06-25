package basicConcepts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job1 = launch {
            println("Job 1 launching")
            delay(3000L)
            println("Job 1 launched")

            val job2 = launch {
                println("Job 2 launching")
                delay(3000L)
                println("Job 2 launched")
            }
            job2.invokeOnCompletion { println("Job 2 completed") }

            val job3 = launch {
                println("Job 3 launching")
                delay(3000L)
                println("Job 3 launched")
            }
            job3.invokeOnCompletion { println("Job 3 completed") }

        }

        job1.invokeOnCompletion { println("Job 1 completed") }
        //Uncomment to cancell job
//        delay(500L)
//        println("Job1 will be cancelled")
        // Job 2 and 3 wont run since job 1 the parent is being cancelled
        //job1.cancel()
    }
}
