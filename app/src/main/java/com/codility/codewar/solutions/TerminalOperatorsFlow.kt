package com.codility.codewar.solutions

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    flowVSCollections()
}

private fun flowVSCollections() {
    val flow = flow {
        kotlinx.coroutines.delay(100)

        println("Emitting first value")
        emit(1)

        kotlinx.coroutines.delay(100)

        println("Emitting second value")
        emit(2)
    }

    // Receives all emitions
    runBlocking {
        flow.collect { receivedValue ->
            println("Received value $receivedValue")
        }
    }

    // returns first emition
    runBlocking {
        val item = flow.firstOrNull()
        println("Received $item")
    }

    println("-------------------------")

    runBlocking {
        val item = flow.first{ it > 1 }
        println("Received $item")
    }

    println("-------------------------")

    // returns last emition
    runBlocking {
        val item = flow.last()
        println("Received $item")
    }

    println("-------------------------")

    // waits until flow complete and then returns all emittions as a list or set
    runBlocking {
        val item = flow.toSet()
        println("Received $item")
    }

    println("-------------------------")

    runBlocking {
        val item = flow.toList()
        println("Received $item")
    }

    println("-------------------------")

    // Used to perform operations like summing up emitions of a flow
    runBlocking {
        val item = flow.fold(0) { accumulator, emittedValue ->
            accumulator + emittedValue
        }
        println("Received $item")
    }
}