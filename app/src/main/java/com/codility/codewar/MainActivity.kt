package com.codility.codewar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.regex.Pattern
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

	}
}

fun main(args: Array<String>) {
	println("Number of months is -${"".getInitials()}-")
	println("Number of months is -${" ".getInitials()}-")
	println("Number of months is -${"Kelvin  ".getInitials()}-")
	println("Number of months is -${"Kelvin  Kioko".getInitials()}-")
}

fun String?.getInitials(): String {
	var initials = ""

	this?.let {
		// Clear unnecessary white spaces i.e Kelvin   Kioko to Kelvin Kioko
		val name = it.trim().replace("\\s+".toRegex(), " ")
		val nameArray = name.split(" ")
		initials = when (nameArray.size) {
			1 -> nameArray[0].firstChar()
			2 -> "${nameArray[0].firstChar()}${nameArray[1].firstChar()}"
			else -> "${nameArray[0].firstChar()}${nameArray[1].firstChar()}"
		}
		return initials
	} ?: run {
		return initials
	}
}

// Splitting a string with white space " " creates an array with 1 empty string
// If we try to get the first char it will crash because string index out of reach or something of the sort
fun String.firstChar(): String = if (this.isBlank() || this.isEmpty()) "" else this[0].toString()

fun validateOpeningAmount(
	amount: Float,
	minimumAmount: Float,
	maximumAmount: Float
): Boolean {
	return amount in minimumAmount..maximumAmount
}

fun String.getDateSuperScript(): String {
	return when (this.toInt() % 10) {
		1 -> "${this}st"
		2 -> "${this}nd"
		3 -> "${this}rd"
		else -> "${this}th"
	}
}


fun getMonths(months: Int): Long {
	val calendarInstance = Calendar.getInstance()
	val startDate = Calendar.getInstance()
	val endDate = Calendar.getInstance()
	startDate.time = Date(calendarInstance.timeInMillis)
	endDate.time = Date(calendarInstance.timeInMillis + (2592000000 * months))

	val startMonth = if (startDate.get(Calendar.MONTH) > 0) startDate.get(Calendar.MONTH) - 1 else startDate.get(Calendar.MONTH)
	val endMonth = if (endDate.get(Calendar.MONTH) > 0) endDate.get(Calendar.MONTH) - 1 else endDate.get(Calendar.MONTH)

	val yearsInBetween = (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR))
	val monthsDiff = endMonth - startMonth
	val ageInMonths = (yearsInBetween * 12 + monthsDiff).toLong()

	return ageInMonths / 3
}

fun setUpLabels() = mapOf(
		R.string.available_balance to "Available balance",
		R.string.every to "Every"
	)

fun Float.toEditable(decimalPoints: Int = 0) = "%.${decimalPoints}f".format(this)

fun Float.formatAmount(): String {
	if (this.toString() == "0.0") return "0.00"
	val pattern = "#,###,###,###.00"
	val decFormat = DecimalFormat(pattern)
	return decFormat.format(this)
}

private val dr = ("/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n"
		+ "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n <P Salinger> Main Street, +1-098-512-2222, Denver\n"
		+ "<Q Salinge> Main Street, +1-541-754-3011, Denve\n" + "<R Salinge> Main Street, +1-098-512-2222, Denve\n"
		+ "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n")

private fun testModulus() {
	val formatOne = "/+1-541-754-3010 156 Alphand_St. <J Steeve>"
	val formatTwo = "133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010!"
	val formatThree = "<Anastasia> +48-421-674-8974 Via Quirinal Roma"

	val phoneNumberRegex = "\\+\\d{1,2}-\\d{3}-\\d{3}-\\d{4}".toRegex(setOf(RegexOption.IGNORE_CASE))
	val nameRegex = "<(.+?)>".toRegex(setOf(RegexOption.IGNORE_CASE))
	val cleanRegex = "[/:!$;*\\s+_]".toRegex(setOf(RegexOption.IGNORE_CASE))

	val phoneBookLines = dr.lines()

	val occurrence = phoneBookLines.find { "+1-541-754-3010" in it }
	val name = occurrence?.substring(startIndex = occurrence.indexOf("<") + 1, endIndex = occurrence.indexOf(">")) ?: ""
	val m = Pattern.compile("\\+\\d{1,2}-\\d{3}-\\d{3}-\\d{4}").matcher(occurrence ?: "")
	var v = ""
	while (m.find()) { v = m.group() ?: "" }

	val address = occurrence?.replace(phoneNumberRegex, "")?.replace(nameRegex, "")?.replace(cleanRegex, " ")?.trim()

	println(phoneBookLines)
	println(occurrence)
	println(name)
	println(v)
	println(address)
}

