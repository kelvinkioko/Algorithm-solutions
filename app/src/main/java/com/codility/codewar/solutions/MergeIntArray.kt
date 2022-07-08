package com.codility.codewar.solutions

fun main(args: Array<String>) {
    mergeIntArray(nums1 = intArrayOf(1,2,3,0,0,0), m = 3, nums2 = intArrayOf(2,5,6), n = 3)
    mergeIntArray(nums1 = intArrayOf(1,4,6,0,0,0), m = 3, nums2 = intArrayOf(2, 3, 5), n = 3)
    mergeIntArray(nums1 = intArrayOf(1), m = 1, nums2 = intArrayOf(), n = 0)
    mergeIntArray(nums1 = intArrayOf(0), m = 0, nums2 = intArrayOf(1), n = 1)
    mergeIntArray(nums1 = intArrayOf(4,5,6,0,0,0), m = 3, nums2 = intArrayOf(1,2,3), n = 3)
}

fun mergeIntArray(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
    var finalIndex = nums1.size - 1
    var mIndex = m - 1
    var nIndex = n - 1

    while (mIndex >= 0 && nIndex >= 0) {
        nums1[finalIndex --] = if (nums1[mIndex] > nums2[nIndex])
            nums1[mIndex --]
        else
            nums2[nIndex --]
    }

    while (mIndex >= 0) nums1[finalIndex --] = nums1[mIndex --]
    while (nIndex >= 0) nums1[finalIndex --] = nums2[nIndex --]

    return nums1
}
