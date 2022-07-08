package com.codility.codewar

import java.text.DecimalFormat
import java.util.Calendar
import java.util.Date
import java.util.Stack
import java.util.regex.Pattern

fun main(args: Array<String>) {
	// println("Searching challenge ${searchingChallenge(str = "2aabbcbbbadef")}")
}

fun isMatch(s: String = "ab", p: String = ".*"): Boolean = p.toRegex().matches(s)

fun twoSumPro(nums: IntArray = intArrayOf(3,2,4), target: Int = 6): IntArray {
	val diffMap = mutableMapOf<Int, Int>()
	nums.forEachIndexed { index, int ->
		println("Number of months is *$int*$index")
		println("Number of months is *${diffMap}*")
		diffMap[int]?.let { return intArrayOf(it, index) }
		diffMap[target - int] = index
	}
	return intArrayOf()
}

fun twoSum(nums: IntArray = intArrayOf(3,2,4), target: Int = 6): IntArray {
	var indicesArray: IntArray = intArrayOf()
	firstLoop@for (index in nums.indices) {
		println("Number of months is *${nums[index]}*")
		val number = nums.find { it == target - nums[index] }
		if (number != null) {
			indicesArray = intArrayOf(index, nums.indexOf(number))
			break@firstLoop
		}
	}
	return indicesArray
}

fun String?.getInitials(): String {
	var initials = ""

//	for (nIndex in index + 1 until nums.size) {
//		val secondNumber = nums[nIndex]
//		if (firstNumber + secondNumber == target) {
//			indicesArray = intArrayOf(index, nIndex)
//			break@firstLoop
//		}
//	}

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

