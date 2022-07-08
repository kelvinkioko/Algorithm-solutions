package com.codility.codewar.solutions

fun main(args: Array<String>) {
    characterSet(str = "egrt")
}

fun characterSet(str: String): String {
    val charSet = charArrayOf('q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m')
    val alphaSet = charArrayOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
    var output = ""

    for (i in str.indices) {
        output += alphaSet[charSet.indexOf(str[i])]
    }
    return output
}
