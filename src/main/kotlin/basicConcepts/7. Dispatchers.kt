package basicConcepts

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        //Wont work without specifying a basicConcepts.main thread
//        launch(Dispatchers.Main) {
//            println("Main dispatcher. Thread: ${Thread.currentThread(). name}")
//        }

        launch(Dispatchers.Unconfined) {
            println("Unconfined dispatcher. Thread: ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined2 dispatcher. Thread: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Default dispatcher. Thread: ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            println("IO dispatcher. Thread: ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("My new thread")) {
            println("newSingleThreadContext dispatcher. Thread: ${Thread.currentThread().name}")
        }

    }
}