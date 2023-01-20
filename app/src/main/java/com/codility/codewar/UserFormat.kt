package com.codility.codewar

fun main(args: Array<String>) {

    val (name, email) = SomeUser()
    val (anotherName, anotherEmail) = AnotherClass()

    println("The result is $name, $email")
    println("The another result is $anotherName, $anotherEmail")
}

data class SomeUser(
    val name: String = "Olua Designer",
    val email: String = "odesigner@gmail.com"
)

data class AnotherClass(
    val name: String = "Olua Designer",
    val email: String = "odesigner@gmail.com",
    val location: String = "Nairobi"
)