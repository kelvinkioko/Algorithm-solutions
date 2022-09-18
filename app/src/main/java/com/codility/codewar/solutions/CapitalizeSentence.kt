package com.codility.codewar.solutions

import java.util.Locale

fun main(args: Array<String>) {
    val flower = "flower"
    val house = "this is a house"

    println(capitalizeSentence(str = flower))
    println(capitalizeSentence(str = house))
}
private fun capitalizeSentence(str: String): String {
    return str
        .split(" ")
        .joinToString(" ") { singleString ->
            singleString.replaceFirstChar { char ->
                char.titlecase(Locale.getDefault())
            }
        }
}
