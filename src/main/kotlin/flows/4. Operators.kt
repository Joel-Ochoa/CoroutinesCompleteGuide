package flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        //        flows.mapOperator()
//        flows.filterOperator()
//        flows.transformOperator()
//        flows.takeOperator()
//    flows.reduceOperator()
        flowOnOperator()
    }
}

private suspend fun mapOperator() {
    (1..10).asFlow()
        .map {
            delay(500)
            "mapping $it"
        }
        .collect {
            println(it)
        }
}

private suspend fun filterOperator() {
    (1..10).asFlow()
        .filter { it % 2 == 0 }
        .collect {
            println("Filtered number $it")
        }
}

private suspend fun transformOperator() {
    (1..10).asFlow()
        .transform {
            emit("Emmitting string value $it")
            emit(it)
        }
        .collect() {
            println(it)
        }
}

private suspend fun takeOperator() {
    (1..10).asFlow()
        .take(2)
        .collect { println(it) }
}

private suspend fun reduceOperator() {
    val size = 2
    val factorial = (1..size).asFlow()
        .reduce { accumulator, value ->
            accumulator * value
        }
    println("Factorial of $size is $factorial ")
}

private suspend fun flowOnOperator() {
    (1..10).asFlow()
        .flowOn(Dispatchers.IO)
        .collect {
            print(it)
        }
}