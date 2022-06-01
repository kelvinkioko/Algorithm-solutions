package com.codility.codewar

import androidx.appcompat.app.AppCompatActivity
import java.util.Collections
import kotlin.math.roundToInt

class Backup : AppCompatActivity() {
    private fun solution(N: Int, S: String): Int {
        var availableSlot = 0
        val allocatedSeats = S.split(" ")
        val seatRightLabels = arrayOf("A", "B", "C")
        val seatMiddleLabels = arrayOf("D", "E", "F", "G")
        val seatLeftLabels = arrayOf("H", "J", "K")

        val match = "photo.jpg, Warsaw, 2013-09-05 14:08:15"

        println("String MATCH ${match.matches("\\w{1,20}\\.\\w{3,4}, \\w{1,20}, \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}".toRegex(setOf(RegexOption.IGNORE_CASE)))}")

        for (i in 1 until N + 1) {
            val rightIsleOptions = arrayOf("$i${seatRightLabels[1]}", "$i${seatRightLabels[2]}", "$i${seatMiddleLabels[0]}", "$i${seatMiddleLabels[1]}")

            val rightIsleSeatsAllocated = allocatedSeats.filter { allocated -> rightIsleOptions.any { rightOptions -> rightOptions == allocated } }

            val leftIsleOptions = arrayOf("$i${seatLeftLabels[0]}", "$i${seatLeftLabels[1]}", "$i${seatMiddleLabels[2]}", "$i${seatMiddleLabels[3]}")

            val leftIsleSeatsAllocated = allocatedSeats.filter { allocated -> leftIsleOptions.any { leftOptions -> leftOptions == allocated } }

            if (rightIsleSeatsAllocated.isEmpty() && leftIsleSeatsAllocated.isEmpty()) {
                availableSlot += 2
            } else if (rightIsleSeatsAllocated.isEmpty() || leftIsleSeatsAllocated.isEmpty()) {
                availableSlot += 1
            } else {
                var middleRowAvailable = true
                seatMiddleLabels.forEach {  label ->
                    if (allocatedSeats.contains("$i$label")){
                        middleRowAvailable = false
                    }
                }
                if (middleRowAvailable) {
                    availableSlot += 1
                }
            }
        }

        return availableSlot
    }

//      private fun solution(A: IntArray): Int {
//              var smallestMissingNumber = 1
//
//              while(A.contains(smallestMissingNumber)) {
//                      smallestMissingNumber++
//              }
//              return smallestMissingNumber
//      }

    private fun anotherSolution(A: IntArray, K: Int): Boolean {
        val n = A.size
        for (i in 0 until n - 1) {
            println("Solution returned i $i")

            if (A[i] > A[i + 1]) {
                println("Solution returned i false")
                return false
            }
        }
        println("Solution returned  A[n - 1] != K ${ A[n - 1] != K}")
        println("Solution returned  A[0] != 1 ${A[0] != 1}")
        if (A[0] >= 1 && A[n - 1] != K) {
            println("Solution returned - false")
            return false
        } else {
            println("Solution returned - true")
            return true
        }
    }

    fun solutioner(A: IntArray, K: Int): Boolean {
        val n = A.size
        for (i in 0 until n - 1) {
            println("Solution returned i $i")

            if (A[i] > A[i + 1]) {
                println("Solution returned i false")
                return false
            }
        }
        println("Solution returned  A[n - 1] != K ${ A[n - 1] != K}")
        println("Solution returned  A[0] != 1 ${A[0] != 1}")
        if (A[0] >= 1 && A[n - 1] != K) {
            println("Solution returned - false")
            return false
        } else {
            println("Solution returned - true")
            return true
        }
    }

    private fun binaryGapSolution(N: Int): Int {
        val binaryValue = Integer.toBinaryString(N)
        val filter = binaryValue.length - (binaryValue.replace("1", "").length)

        return if (filter > 1) {
            var solidStateBinaryGap = 0
            var binaryGap = 0
            for (i in binaryValue.indices) {
                if (binaryValue[i].toString().equals("0", ignoreCase = true)) {
                    binaryGap += 1
                } else {
                    if (binaryGap > solidStateBinaryGap) {
                        solidStateBinaryGap = binaryGap
                    }
                    binaryGap = 0
                }
            }
            solidStateBinaryGap
        } else {
            0
        }
    }

    private fun rotateSolution(A: IntArray, K: Int): IntArray {
        // write your code in Kotlin
        val arrayList = A.toList()
        Collections.rotate(arrayList, K)
        println("Rotate array ${arrayList.toIntArray().joinToString()}")
        return arrayList.toIntArray()
    }

    // Roman Numerals Encoder
    private fun ref(num: Int): String {
        tailrec fun encodeAux(num: Int, acc: String): String =
            if (num == 0) {
                acc
            } else {
                val (k, v) = numerals.first { it.second <= num }
                encodeAux(num - v, acc + k)
            }
        return encodeAux(num, "")
    }

    private val numerals = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I").zip(
        listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    )

    //My solution for Numerals Encoder
    val thousandth = arrayOf("M", "MM", "MMM")
    val hundredth = arrayOf("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val tenth = arrayOf("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val onenth = arrayOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")

    fun encode(num: Int): String {
        val numLength = num.toString().length
        val numArray = num.toString().map { it.toString() }.toList()

        println("Length of num $numLength")
        println("Length of array $numArray")
        var romanNumber = ""
        if (numLength >= 1 && numArray[numLength - 1].toInt() != 0) {
            romanNumber = "${onenth[numArray[numLength - 1].toInt() - 1]}$romanNumber"
        }
        if (numLength >= 2 && numArray[numLength - 2].toInt() != 0) {
            romanNumber = "${tenth[numArray[numLength - 2].toInt() - 1]}$romanNumber"
        }
        if (numLength >= 3 && numArray[numLength - 3].toInt() != 0) {
            romanNumber = "${hundredth[numArray[numLength - 3].toInt() - 1]}$romanNumber"
        }
        if (numLength >= 4 && numArray[numLength - 4].toInt() != 0) {
            romanNumber = "${thousandth[numArray[numLength - 4].toInt() - 1]}$romanNumber"
        }

        return romanNumber
    }


    /**
     * 1234
     *
     * 1 oneth
     * 2 tenth
     * 3 hund
     * 4 thound
     *
     */

    /**
     * Reverse words function
     */
    fun spinWordsOptimized(sentence: String) =
        sentence
            .split(" ")
            .joinToString(" ") {
                if(it.length >= 5 && "^[A-Za-z]*$".toRegex().matches(it))
                    it.reversed()
                else
                    it
            }

    private fun spinWords(sentence: String): String {
        var newSentence = ""

        sentence
            .split(" ")
            .forEach {
                val regex = "^[A-Za-z]*$".toRegex()
                newSentence += if (it.length >= 5 && regex.matches(it)) "${it.reversed()} " else "$it "
            }

        return newSentence.removeSuffix(" ")
    }

    /**
     * Take an integer n (n >= 0) and a digit d (0 <= d <= 9) as an integer.
     * Square all numbers k (0 <= k <= n) between 0 and n.
     * Count the numbers of digits d used in the writing of all the k**2.
     * Call nb_dig (or nbDig or ...) the function taking n and d as parameters and returning this count.
     */

    fun nbDig(n:Int, d:Int):Int {
        var numberAppearance = 0
        return if (n < 0 || d < 0) {
            numberAppearance
        } else {
            IntArray(n + 1) {
                numberAppearance += (it * it).toString().count{ d.toString().contains(it) }
                it * it
            }
            numberAppearance
        }
    }

    fun numberDig(n: Int, d: Int): Int = (0..n).joinToString { "${it * it}" }.count { "$it" == "$d" }

    /**
     * Complete the method/function so that it converts dash/underscore delimited words into camel casing.
     * The first word within the output should be capitalized only if the original word was capitalized
     * (known as Upper Camel Case, also often referred to as Pascal case).
     *
     * println("toCamelCase ${toCamelCase(str = "The-stealth_warrior")}")
     *
     * "the-stealth-warrior" gets converted to "theStealthWarrior"
     */

    // Best solution
    fun toCamelCaseBestSolution(str: String) =
        str.split('-', '_').mapIndexed { i, it -> if (i != 0) it.capitalize() else it }.joinToString("")


    // My solution
    fun toCamelCase(str:String):String = if(str.isNotEmpty()) str.split("_", "-", ignoreCase = true).joinToString("") { it.capitalize() }.replaceFirst(str[0].toUpperCase(), str[0]) else str

    /**
     * josephus_survivor(7,3) => means 7 people in a circle;
     * one every 3 is eliminated until one remains
     * [1,2,3,4,5,6,7] - initial sequence
     * [1,2,4,5,6,7] => 3 is counted out
     * [1,2,4,5,7] => 6 is counted out
     * [1,4,5,7] => 2 is counted out
     * [1,4,5] => 7 is counted out
     * [1,4] => 5 is counted out
     * [4] => 1 counted out, 4 is the last element - the survivor!
     **/

    fun josephusSurvivor(n: Int, k: Int): Int {
        var nArray = IntArray(n) { it + 1 }.toList()
        return if (nArray.size == 1) {
            n
        } else {
            do {
                Collections.rotate(nArray, -(k - 1))
                nArray = nArray.drop(1)
            } while (nArray.size != 1)

            nArray[0]
        }
    }

    /**
     * int.coerceIn forces the integer to be within the set bounds
     * if int -s -10 it gets upgraded to the lowest bound which is 0
     * same applies to a number higher than 255
     */
    fun rgb(r: Int, g: Int, b: Int): String = String.format(
        "%02X%02X%02X",
        r.coerceIn(0..255),
        g.coerceIn(0..255),
        b.coerceIn(0..255))


    /**
     * Help the bookseller !
     */
    object StockListClever {
        fun stockSummary(lstOfArt: Array<String>, lstOfCat: Array<String>): String {
            if (lstOfArt.isEmpty()) return ""
            val counts = lstOfArt.groupingBy { it.take(1) }.fold(0) { acc, s -> acc + s.split(" ")[1].toInt() }
            return lstOfCat.joinToString(" - ") { "($it : ${counts.getOrDefault(it, 0)})" }
        }
    }

    object StockList {
        fun stockSummary(listOfArt: Array<String>, listOfCat: Array<String>): String {

            return if (listOfArt.isEmpty() || listOfCat.isEmpty()) {
                ""
            } else {
                val finalResponse = mutableListOf<String>()

                listOfCat.forEach { category ->
                    var count = 0
                    listOfArt.filter { it.startsWith(category, ignoreCase = true) }.map {
                        count += it.split(" ")[1].toInt()
                    }
                    finalResponse.add("($category : ${count})")
                }

                finalResponse.joinToString(" - ")
            }
        }
    }

    fun inArray(array1: Array<String>, array2: Array<String>): Array<String> {
        val r = mutableListOf<String>()
        if (array1.isEmpty() || array2.isEmpty()) return r.toTypedArray()
        array2.forEach { string ->
            array1.map { stringOne ->
                if (stringOne in string && !r.contains(stringOne)) r.apply { add(stringOne); sort() }
            }
        }

        return r.toTypedArray()

        // Solution 2
        return array1.filter{e->array2.any{it.contains(e)}}.distinct().sorted().toTypedArray()
    }

    object BuyCar {
        fun nbMonths(
            startPriceOld: Int = 2000,
            startPriceNew: Int = 8000,
            savingperMonth: Int = 1000,
            percentLossByMonth: Double = 1.5
        ): Pair<Int, Int> {
            println("startPriceOld $startPriceOld")
            if (startPriceOld > startPriceNew) return Pair(0, startPriceOld - startPriceNew)

            var monthPercentageLossIncreaseCounter = 0
            val savingProgress = SavingProgress(
                oldVehiclePrice = startPriceOld.toDouble(),
                newVehiclePrice = startPriceNew.toDouble(),
                percentLossByMonth = percentLossByMonth
            )

            var canIAffordThis: Boolean

            do {
                if (monthPercentageLossIncreaseCounter >= 1) {
                    monthPercentageLossIncreaseCounter = 0
                    savingProgress.percentLossByMonth = savingProgress.percentLossByMonth + 0.5
                } else {
                    monthPercentageLossIncreaseCounter = 1
                }

                savingProgress.apply {
                    oldVehiclePrice *= ((100 - savingProgress.percentLossByMonth) / 100)
                    newVehiclePrice *= ((100 - savingProgress.percentLossByMonth) / 100)
                    monthlySavings += savingperMonth
                }
                canIAffordThis = ((savingProgress.oldVehiclePrice + savingProgress.monthlySavings) >= savingProgress.newVehiclePrice)
            } while (!canIAffordThis)

            val months = savingProgress.monthlySavings / savingperMonth
            val remainder = (savingProgress.oldVehiclePrice + savingProgress.monthlySavings) - savingProgress.newVehiclePrice

            return Pair(months.roundToInt(), remainder.roundToInt())
        }
    }

    data class SavingProgress(
        var oldVehiclePrice: Double = 0.0,
        var newVehiclePrice: Double = 0.0,
        var percentLossByMonth: Double = 0.0,
        var monthlySavings: Double = 0.0
    )
}



fun main(args: Array<String>) {
    val prop = "abc"::length
    println(prop.get())
}

fun productFibBestSolution(prod: Long):LongArray {
    var (a, b, check) = longArrayOf(0, 1, 0)
    while (check < prod) {
        a = b.also { b += a }
        check = a * b
    }
    return longArrayOf(a, b, if (check == prod) 1L else 0L)
}

fun productFib(prod: Long) : LongArray {
    val fibStack = mutableListOf<Long>(0, 1)

    tailrec fun encodeAux() : LongArray {
        fibStack.add(fibStack[fibStack.size - 1] + fibStack[fibStack.size - 2])

        val prodCompare = fibStack[fibStack.size - 1].toULong() * fibStack[fibStack.size - 2].toULong()

        return when {
            prodCompare.toLong() == prod ->
                longArrayOf(
                    fibStack[fibStack.size - 2],
                    fibStack[fibStack.size - 1],
                    1
                )
            prodCompare.toLong() > prod ->
                longArrayOf(
                    fibStack[fibStack.size - 2],
                    fibStack[fibStack.size - 1],
                    0
                )
            else -> {
                encodeAux()
            }
        }
    }

    return encodeAux()
}

fun solution(A: IntArray): Int {
    val max = A.indices.map { i: Int -> A[i] }.maxOrNull()

    var solutionAnswer = 0
    if (max == null || max <= 0) {
        solutionAnswer = 1
    } else {
        for (x in 1 until (max + 2)) {
            if (!A.contains(x)) {
                solutionAnswer = x
                break
            }
        }
    }

    return solutionAnswer
}
