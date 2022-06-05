package com.codility.codewar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.util.Calendar
import java.util.Date
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

	}
}

fun main(args: Array<String>) {
	println("romanToInt *${romanToInt(s = "MCMXCIV")}-")
	println("romanToInt *${romanToInt(s = "III")}-")
	println("romanToInt *${romanToInt(s = "LVIII")}-")
	// println("ListNode -${isPalindrome(x = -121)}-")
}

fun romanToInt(s: String = "MCMXCIV"): Int {
	val romanMap: HashMap<String, Int> = hashMapOf("M" to 1000, "D" to 500, "C" to 100, "L" to 50, "X" to 10, "V" to 5, "I" to 1)
	var romanInt = 0

	for (n in s.indices) {
		if (n + 1 < s.length && (romanMap[s[n].toString()] ?: 0) < (romanMap[s[n + 1].toString()] ?: 0)) {
			romanInt -= romanMap[s[n].toString()] ?: 0
		} else {
			romanInt += romanMap[s[n].toString()] ?: 0
		}
	}
	return romanInt
}

fun isPalindrome(x: Int): Boolean {
	// Solution one
	var xx = x

	if (xx < 0 || xx != 0 && xx % 10 == 0)
		return false

	var res = 0
	while (xx > res) {
		res = res * 10 + xx % 10
		xx /= 10
	}

	return xx == res || xx == res / 10

//  Solution Two
//	var index = x.toString().length - 1
//	var reversedX = ""
//
//	while (index >= 0) {
//		reversedX = "$reversedX${x.toString()[index]}"
//		index--
//	}
//
//	return x.toString() == reversedX
}

fun isMatch(s: String = "ab", p: String = ".*"): Boolean = p.toRegex().matches(s)

fun findMedianSortedArrays(nums1: IntArray = intArrayOf(1, 3), nums2: IntArray = intArrayOf(2, 4)): Double {
	val mergedArray = nums1.plus(nums2).sortedArray()
	val totalSize = mergedArray.size
	val midPoint = Math.round(totalSize.toDouble() / 2).toInt()

	val median : Double = if (totalSize % 2 > 0) {
		mergedArray[midPoint - 1].toDouble()
	} else {
		(mergedArray[midPoint - 1] + mergedArray[midPoint]).toDouble() / 2
	}
	return median
}

fun lengthOfLongestSubstring(s: String = "dfdhfkiff"): Int {
	var maxRun = 0
	var currentRun = 0
	val lastBuilder = StringBuilder()
	s.forEach { char ->
		if (lastBuilder.contains(char)) {
			val lastIndex = lastBuilder.indexOf(char) + 1
			lastBuilder.delete(0, lastIndex)
			lastBuilder.append(char)
			currentRun = lastBuilder.length
		} else {
			lastBuilder.append(char)
			currentRun ++
		}
		maxRun = if (maxRun > currentRun) maxRun else currentRun
	}

	return maxRun
}

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

