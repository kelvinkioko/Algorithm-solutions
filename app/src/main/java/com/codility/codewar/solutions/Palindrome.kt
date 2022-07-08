package com.codility.codewar.solutions

fun main(args: Array<String>) {
    isPalindrome(x = 101)
    isPalindrome(x = 1011)
}

fun isPalindrome(x: Int): Boolean {
    // Solution one
    var xx = x

    if (xx < 0 || xx != 0 && xx % 10 == 0)
        return false

    var res = 0
    while (xx > res) {
        res = res * 10 + xx % 10
        xx /= 10
    }

    return xx == res || xx == res / 10

    // region Solution Two
	var index = x.toString().length - 1
	var reversedX = ""

	while (index >= 0) {
		reversedX = "$reversedX${x.toString()[index]}"
		index--
	}

	return x.toString() == reversedX

    // endregion
}
