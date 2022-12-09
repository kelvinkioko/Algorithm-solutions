package com.codility.codewar.solutions

import kotlin.random.Random

fun main(args: Array<String>) {
    val persons = createDummyData()

    println(persons.createMap())
}

// AssociateBy()
private fun List<Person>.createMap(): Map<String, Person> {
    return this.associateBy { person -> person.name }
}

private fun mapFlattenFlatMap() {
    val persons = createDummyData()
    // Map - one to one function. Change one data structure to another or one data type to another
    val list = listOf("abc", "def", "ghi")
    println(list.map { "$it - post" })

    // Flatten
    val map: List<List<Int>> = persons.map { it.mobileNumbers } // n
    println("Mobile numbers M $map")
    val flattenMappedNumbers: List<Int> = persons.map { it.mobileNumbers }.flatten() // n + n*n
    println("Mobile numbers M F $flattenMappedNumbers")
    val flatMapNumbers: List<Int> = persons.flatMap { it.mobileNumbers } // n*n
    println("Mobile numbers FM $flatMapNumbers")
}

data class Person(val name: String, val age: Int, val sex: Boolean, val mobileNumbers: List<Int>)

fun createDummyData(): List<Person> {
    val list = mutableListOf<Person>()
    repeat(10) {
        val name = (65 + it).toChar().toString()
        val age = Random.nextInt(80)
        val sex = it % 2 == 0
        list.add(
            Person(
                name = name,
                age = age,
                sex = sex,
                mobileNumbers = listOf(age * 6, age * 4, age * 9)
            )
        )
    }
    return list
}