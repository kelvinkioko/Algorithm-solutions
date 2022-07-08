package com.codility.codewar.solutions

fun main(args: Array<String>) {
    "22".getDateSuperScript()
}

fun String.getDateSuperScript(): String {
    return when (this.toInt() % 10) {
        1 -> "${this}st"
        2 -> "${this}nd"
        3 -> "${this}rd"
        else -> "${this}th"
    }
}
