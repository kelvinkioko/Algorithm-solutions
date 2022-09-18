package com.codility.codewar

import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    // Declaring two date strings
    val mDate1 = "09/15/2022"
    val mDate2 = "11/20/2023"

    // Creating a date format
    val mDateFormat = SimpleDateFormat("MM/dd/yyyy")

    // Converting the dates
    // from string to date format
    val mDate11 = mDateFormat.parse(mDate1)
    val mDate22 = mDateFormat.parse(mDate2)

    // Finding the absolute difference between
    // the two dates.time (in milli seconds)
    val mDifference = kotlin.math.abs(mDate11.time - mDate22.time)

    // Converting milli seconds to dates
    val mDifferenceDates = mDifference / (24 * 60 * 60 * 1000)

    // Converting the above integer to string
    val mDayDifference = mDifferenceDates.toString()

    // Displaying the result in the TextView
    println("The difference between two dates is $mDayDifference days")
}
