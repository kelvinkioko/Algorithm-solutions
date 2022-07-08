package com.codility.codewar.solutions

fun main(args: Array<String>) {
    romanToInt(s = "MCMXCIV")
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
