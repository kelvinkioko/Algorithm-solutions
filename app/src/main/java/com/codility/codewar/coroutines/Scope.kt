package com.codility.codewar.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {


    contextCode()

    // scopeTest()
}

private fun contextCode() {
    runBlocking {
        launch(CoroutineName("myCoroutine")) {
            println("This is run from ${coroutineContext[CoroutineName]}")
        }
    }
}

private fun scopeTest() {
    println("Program execution will now block")
    runBlocking {
        launch {
            delay(1000L)
            println("Task from runBlocking")
        }

        GlobalScope.launch {
            delay(500L)
            println("Task from GlobalScope")
        }

        coroutineScope {
            launch {
                delay(1500L)
                println("Task from coroutineScope")
            }
        }
    }
    println("Program execution will now continue")
}

