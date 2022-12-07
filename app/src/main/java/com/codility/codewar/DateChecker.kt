package com.codility.codewar

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun main(args: Array<String>) {
    val dateDifference0 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2022-09-14T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference0)
    val dateDifference1 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2023-10-17T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference1)
    val dateDifference2 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2023-10-15T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference2)
    val dateDifference3 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2023-09-16T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference3)
    val dateDifference4 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2023-09-15T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference4)
    val dateDifference5 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2023-11-20T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference5)
    val dateDifference6 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2023-07-13T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference6)
    val dateDifference7 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2024-05-12T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference7)
    val dateDifference8 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2024-11-11T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference8)
    val dateDifference9 = DateDifference(
        firstDate = "2022-09-15T00:00:00".toDate(),
        finalDate = "2022-09-20T00:00:00".toDate()
    ).dateDifference()
    println(dateDifference9)

}

private fun String.toDate(): Long = SimpleDateFormat(
    "yyyy-MM-dd'T'HH:mm:ss",
    Locale.getDefault()
).parse(this)?.time ?: 0L
