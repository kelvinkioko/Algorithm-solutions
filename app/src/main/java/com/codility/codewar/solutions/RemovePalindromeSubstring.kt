package com.codility.codewar.solutions

fun main(args: Array<String>) {
    removePalindromeSub(s = "ababa")
    removePalindromeSub(s = "abb")
    removePalindromeSub(s = "bab")
    removePalindromeSub(s = "baabb")
}

fun removePalindromeSub(s: String): Int {
    /**
     * Test cases
     * removePalindromeSub(s = "ababa")
     * removePalindromeSub(s = "abb")
     * removePalindromeSub(s = "bab")
     * removePalindromeSub(s = "baabb")
     */
    if (s.isEmpty()) return 0

    var i = 0
    var j = s.length - 1
    while (i < j) {
        if (s[i++] != s[j--])
            return 2
    }

    return 1
}
