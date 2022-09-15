package com.codility.codewar

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.days

fun main(args: Array<String>) {
    val firstDate = "2022-09-14T00:00:00".toDate()
    val finalDate = "2023-11-06T00:00:00".toDate()

    val startDate = Calendar.getInstance()
    val endDate = Calendar.getInstance()

    startDate.time = Date(firstDate)
    endDate.time = Date(finalDate)

    val dateDifference = DateDifferenceModel()

    val yearDiff = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)
    var monthDiff = endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH)
    val dayDiff = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)

    if (yearDiff == 0) {
        if (endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH)) {
            if (monthDiff > 1 && dayDiff > 28) {
                val startMonth = getMonths().filter { it.month == startDate.get(Calendar.MONTH) }[0]

                val startMonthDayDiff = startMonth.month - startDate.get(Calendar.DAY_OF_MONTH)
                val endMonthDayDiff = endDate.get(Calendar.DAY_OF_MONTH)

                monthDiff -= 1
                val sumDaysDiff = startMonthDayDiff + endMonthDayDiff

                val daysDiv = sumDaysDiff / startMonth.month
                val daysModulus = sumDaysDiff % startMonth.month

                dateDifference.month = monthDiff + daysDiv
                dateDifference.day = daysModulus
            } else {
                dateDifference.day = dayDiff
            }
        } else {
            dateDifference.day = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)
        }
    } else {
        if (endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH)) {
            dateDifference.year = yearDiff
            if (monthDiff > 1 && dayDiff > 28) {
                val startMonth = getMonths().filter { it.month == startDate.get(Calendar.MONTH) }[0]

                val startMonthDayDiff = startMonth.month - startDate.get(Calendar.DAY_OF_MONTH)
                val endMonthDayDiff = endDate.get(Calendar.DAY_OF_MONTH)

                monthDiff -= 1
                val sumDaysDiff = startMonthDayDiff + endMonthDayDiff

                val daysDiv = sumDaysDiff / startMonth.month
                val daysModulus = sumDaysDiff % startMonth.month

                dateDifference.month = monthDiff + daysDiv
                dateDifference.day = daysModulus
            } else {
                dateDifference.day = dayDiff
            }
        } else {
            dateDifference.year = yearDiff - 1
            monthDiff = (12 - startDate.get(Calendar.MONTH)) + endDate.get(Calendar.MONTH)
            dateDifference.month = monthDiff
        }
    }
}

private fun String.toDate(): Long =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(this)?.time
        ?: 0L

data class DateDifferenceModel(
    var year: Int = 0,
    var month: Int = 0,
    var day: Int = 0
)

data class MonthsModel(
    var month: Int = 0,
    var days: Int = 0
)

private fun getMonths(): List<MonthsModel> = listOf(
    MonthsModel(0, 31),
    MonthsModel(1, 28),
    MonthsModel(2, 31),
    MonthsModel(3, 30),
    MonthsModel(4, 31),
    MonthsModel(5, 30),
    MonthsModel(6, 31),
    MonthsModel(7, 31),
    MonthsModel(8, 30),
    MonthsModel(9, 31),
    MonthsModel(10, 30),
    MonthsModel(11, 31),
)


/**
 * Deprecated
 */

//    println("Start Year ${startDate.get(Calendar.YEAR)}")
//    println("End Year ${endDate.get(Calendar.YEAR)}")
//    println("Year Diff ${endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)}")
//    println("----------------------------------------------------------------------")
//    println("Start Day ${startDate.get(Calendar.DAY_OF_YEAR)}")
//    println("End Day ${endDate.get(Calendar.DAY_OF_YEAR)}")
//    //println("Date Diff ${endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)}")
//
//    val dayDiff = if ((endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)) > 0) {
//        endDate.get(Calendar.DAY_OF_YEAR) + (365 - startDate.get(Calendar.DAY_OF_YEAR))
//    } else {
//        endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)
//    }
//    println("Date Diff $dayDiff")
//
//    val weeksInBetween = endDate.get(Calendar.WEEK_OF_YEAR) - startDate.get(Calendar.WEEK_OF_YEAR)
//
//    val startMonth =
//        if (startDate.get(Calendar.MONTH) > 0) startDate.get(Calendar.MONTH) - 1 else startDate.get(
//            Calendar.MONTH
//        )
//    val endMonth =
//        if (endDate.get(Calendar.MONTH) > 0) endDate.get(Calendar.MONTH) - 1 else endDate.get(
//            Calendar.MONTH
//        )
//
//    val monthsInBetween = endMonth - startMonth
//    val yearsInBetween = (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR))