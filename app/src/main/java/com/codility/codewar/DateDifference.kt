package com.codility.codewar

import java.util.Calendar
import java.util.Date

class DateDifference(
    val firstDate: Long,
    val finalDate: Long
) {
    private var dateDifference = DateDifferenceModel()
    private val startDate = Calendar.getInstance().also { it.time = Date(firstDate) }
    private val endDate = Calendar.getInstance().also { it.time = Date(finalDate) }

    fun dateDifference() : DateDifferenceModel {
        val yearDiff = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)
        var monthDiff = endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH)
        val dayDiff = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR)

        println(startDate.getActualMaximum(Calendar.MONTH))

        if (yearDiff > 0) {
            dateDifference.year = if (endDate.get(Calendar.MONTH) > startDate.get(Calendar.MONTH))
                yearDiff
            else
                yearDiff - 1
        }

        if (yearDiff == 0 && monthDiff == 0) {
            // Same year and same month, the only difference is days
            dateDifference.day = dayDiff
        } else {
            /** Can be same year but different months or different months and same year or
             * different months and years
             *
             * Check if the end month is greater than the start month and days are more than 28
             * if they are reduce the monthDiff by one
             * if not then the end month is behind the start month which means it falls in the next year so we need to count how many months there are in between
             */
            monthDiff = if (monthDiff > 0 && dayDiff > 28) {
                monthDiff - 1
            } else {
                (11 - startDate.get(Calendar.MONTH)) + endDate.get(Calendar.MONTH)
            }

            /**
             * Here we calculate the number of days between the start date and the end date
             * We'll use that value to calculate the exact days, months and years to present as the final total
             */
            val startMonth = getMonths().filter { it.month == startDate.get(Calendar.MONTH) }[0]
            val startMonthDayDiff = startMonth.days - startDate.get(Calendar.DAY_OF_MONTH)
            val endMonthDayDiff = endDate.get(Calendar.DAY_OF_MONTH)

            val sumDaysDiff = startMonthDayDiff + endMonthDayDiff


            val days = sumDaysDiff % startMonth.days // Will return the number of days left
            val months = monthDiff + (sumDaysDiff / startMonth.days) // Original months + number of extra months we can get from the total number of days between start date and end date
            val years = months / 12 // Number of extra years we can get from the new number of months above

            dateDifference = dateDifference.also {
                it.year += years
                it.month = months % 12
                it.day = days
            }
        }

        return dateDifference
    }

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