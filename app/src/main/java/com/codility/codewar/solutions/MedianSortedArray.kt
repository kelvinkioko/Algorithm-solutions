package com.codility.codewar.solutions

fun main(args: Array<String>) {
    medianSortedArray()
}

fun medianSortedArray(nums1: IntArray = intArrayOf(1, 3), nums2: IntArray = intArrayOf(2, 4)): Double {
    val mergedArray = nums1.plus(nums2).sortedArray()
    val totalSize = mergedArray.size
    val midPoint = Math.round(totalSize.toDouble() / 2).toInt()

    val median : Double = if (totalSize % 2 > 0) {
        mergedArray[midPoint - 1].toDouble()
    } else {
        (mergedArray[midPoint - 1] + mergedArray[midPoint]).toDouble() / 2
    }
    return median
}
