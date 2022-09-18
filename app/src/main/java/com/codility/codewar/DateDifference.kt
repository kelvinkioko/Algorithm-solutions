package com.codility.codewar

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DateDifference(
    firstDate: String,
    finalDate: String,
    val differenceCallBack: ((DateDifferenceModel) -> Unit)
)
{
    var dateDifference = DateDifferenceModel()

    init {
        dateDifference(firstDate = firstDate, finalDate = finalDate)
    }

    private fun dateDifference(firstDate: String, finalDate: String) {
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()

        startDate.time = Date(firstDate.toDate())
        endDate.time = Date(finalDate.toDate())

        val yearDiff = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)
        var monthDiff = endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH)
        val dayDiff = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)

        if (yearDiff > 0) {
            dateDifference.year = if (endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH))
                yearDiff
            else
                yearDiff - 1
        }

        if (yearDiff == 0 && monthDiff == 0) {
            dateDifference.day = dayDiff
        } else {
            monthDiff = if (monthDiff > 1 && dayDiff > 28) {
                monthDiff - 1
            } else {
                (11 - startDate.get(Calendar.MONTH)) + endDate.get(Calendar.MONTH)
            }

            dayMonthDiff(
                monthDiff = monthDiff,
                startDate = startDate,
                endDate = endDate
            )
        }

//        if (endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH) && monthDiff > 1 && dayDiff > 28) {
//            println("if -$firstDate-$finalDate")
//            monthDiff -= 1
//
//            dayMonthDiff(
//                monthDiff = monthDiff,
//                startDate = startDate,
//                endDate = endDate
//            )
//        } else if (yearDiff > 0) {
//            println("else if -$firstDate-$finalDate")
//            monthDiff = (11 - startDate.get(Calendar.MONTH)) + endDate.get(Calendar.MONTH)
//
//            dayMonthDiff(
//                monthDiff = monthDiff,
//                startDate = startDate,
//                endDate = endDate
//            )
//        } else {
//            println("else -$firstDate-$finalDate")
//            dateDifference.day = dayDiff
//        }

        differenceCallBack(dateDifference)
    }

    private fun dayMonthDiff(monthDiff: Int, startDate: Calendar, endDate: Calendar) {
        val startMonth = getMonths().filter { it.month == startDate.get(Calendar.MONTH) }[0]
        val startMonthDayDiff = startMonth.days - startDate.get(Calendar.DAY_OF_MONTH)
        val endMonthDayDiff = endDate.get(Calendar.DAY_OF_MONTH)

        val sumDaysDiff = startMonthDayDiff + endMonthDayDiff

        val daysDiv = sumDaysDiff / startMonth.days
        val daysModulus = sumDaysDiff % startMonth.days

        dateDifference = dateDifference.also {
            it.month = monthDiff + daysDiv
            it.day = daysModulus
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
}