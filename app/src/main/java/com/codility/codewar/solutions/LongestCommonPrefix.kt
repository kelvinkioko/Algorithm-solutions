package com.codility.codewar.solutions

fun main(args: Array<String>) {
    longestCommonPrefix(strs = arrayOf("flower","flow","flight"))
    longestCommonPrefix(strs = arrayOf("dog","racecar","car"))
    longestCommonPrefix(strs = arrayOf("","b"))
    longestCommonPrefix(strs = arrayOf("ab","a"))
    longestCommonPrefix(strs = arrayOf("baab","bacb","b","cbc"))
}


fun longestCommonPrefix(strs: Array<String>): String {
    var prefix = if (strs.isNotEmpty()) strs[0] else ""
    first@for (n in strs.indices) {
        if (strs[n].isEmpty()) {
            prefix = ""
            break@first
        } else {
            // Efficient
            while (strs[n].indexOf(prefix)!=0){
                prefix = prefix.substring(0, prefix.length - 1)
            }
            // Alternative
            var prefixIndex = 0
            var stringIndex = 0
            var matchIndex = 0
            if (prefix.length > strs[n].length) {
                prefix = prefix.dropLast(prefix.length - strs[n].length)
            }
            while (prefixIndex < prefix.length && stringIndex < strs[n].length) {
                if (prefix[matchIndex] != strs[n][matchIndex]) {
                    prefix = prefix.dropLast(prefix.length - matchIndex)
                    if (prefix.isEmpty())
                        break@first
                }
                prefixIndex ++
                stringIndex ++
                matchIndex ++
            }
        }
    }

    return prefix
}
