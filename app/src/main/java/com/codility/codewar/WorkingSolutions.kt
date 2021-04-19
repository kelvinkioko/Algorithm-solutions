package com.codility.codewar

import androidx.appcompat.app.AppCompatActivity
import java.util.Collections

class Backup : AppCompatActivity() {
    private fun solution(N: Int, S: String): Int {
        var availableSlot = 0
        val allocatedSeats = S.split(" ")
        val seatRightLabels = arrayOf("A", "B", "C")
        val seatMiddleLabels = arrayOf("D", "E", "F", "G")
        val seatLeftLabels = arrayOf("H", "J", "K")

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

}
