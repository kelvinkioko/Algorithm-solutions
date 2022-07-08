package com.codility.codewar.solutions

fun main(args: Array<String>) {
    searchingChallenge(str = "2aabbcbbbadef")
}

fun searchingChallenge(str: String): String {
    val k = str[0].digitToInt()
    val s = str.substring(1)

    var uniqueChars = 0
    var count = IntArray(26)

    for (i in s.indices) {
        if (count[s[i] - 'a'] == 0)
            uniqueChars++
        count[s[i] - 'a']++
    }

    count = IntArray(26)

    var charStart = 0
    var subStringStart = 0
    var subStringSize = 1

    for (i in s.indices) {
        count[s[i] - 'a']++

        while (count.filter { it > 0 }.size > k) {
            count[s[charStart] - 'a']--
            charStart++
        }

        if (i - charStart + 1 > subStringSize) {
            subStringSize = i - charStart + 1
            subStringStart = charStart
        }
    }

    return s.substring(subStringStart, subStringStart + subStringSize)
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
