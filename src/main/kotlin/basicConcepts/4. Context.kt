package basicConcepts

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        launch(CoroutineName("MyCoroutine")) {
            println("This is run from ${coroutineContext[CoroutineName.Key]}")

        }

    }

}