package basicConcepts

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


fun main() {
    runBlocking {
        launch(Dispatchers.Default) {
            println("First Context: $coroutineContext")

            //Light weight to switch between processes depending on what we want to do 
            withContext(Dispatchers.IO) {
                println("Second Context: $coroutineContext")
            }
            print("Third Context: $coroutineContext")
        }
    }
}