package com.codility.codewar

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun main(args: Array<String>) {
    DateDifference(
        firstDate = "2022-09-15T00:00:00",
        finalDate = "2023-09-15T00:00:00",
        differenceCallBack = { dateDiff ->
            println(dateDiff)
        }
    )
    DateDifference(
        firstDate = "2022-09-15T00:00:00",
        finalDate = "2023-11-20T00:00:00",
        differenceCallBack = { dateDiff ->
            println(dateDiff)
        }
    )
    DateDifference(
        firstDate = "2022-09-15T00:00:00",
        finalDate = "2023-07-13T00:00:00",
        differenceCallBack = { dateDiff ->
            println(dateDiff)
        }
    )
    DateDifference(
        firstDate = "2022-09-15T00:00:00",
        finalDate = "2024-05-12T00:00:00",
        differenceCallBack = { dateDiff ->
            println(dateDiff)
        }
    )
    DateDifference(
        firstDate = "2022-09-15T00:00:00",
        finalDate = "2024-11-11T00:00:00",
        differenceCallBack = { dateDiff ->
            println(dateDiff)
        }
    )
    DateDifference(
        firstDate = "2022-09-15T00:00:00",
        finalDate = "2022-09-20T00:00:00",
        differenceCallBack = { dateDiff ->
            println(dateDiff)
        }
    )
}
