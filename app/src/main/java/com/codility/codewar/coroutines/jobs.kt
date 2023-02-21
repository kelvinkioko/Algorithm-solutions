package com.codility.codewar.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job1 = launch {
            // delay(3000L)
            println("Job1 launched")
            val job2 = launch {
                println("Job2 launched")
                delay(3000L)
                println("Job2 is finishing")
            }

            job2.invokeOnCompletion {
                println("Job2 isCompleted")
            }

            delay(500L)
            println("Job2 will be cancelled")
            job2.cancel()

            val job3 = launch {
                println("Job3 launched")
                delay(3000L)
                println("Job3 is finishing")
            }

            job3.invokeOnCompletion {
                println("Job3 isCompleted")
            }
        }

        job1.invokeOnCompletion {
            println("Job1 isCompleted")
        }

    }
}