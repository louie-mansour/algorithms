package binarysearch

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
34. Find First and Last Position of Element in Sorted Array
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.
 */

class Solution {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) {
            return listOf(-1, -1).toIntArray()
        }

        var lo = 0
        var hi = nums.size - 1
        var mid: Int

        while (true) {
            if (lo > hi) {
                return listOf(-1, -1).toIntArray()
            }

            mid = (lo + hi) / 2
            if (nums[mid] == target) {
                break
            } else if (nums[mid] < target) {
                lo = mid + 1
            } else {
                hi = mid - 1
            }
        }

        return listOf(
            findFirstTarget(nums.toList(), target, lo, mid),
            findLastTarget(nums.toList(), target, mid, hi)
        ).toIntArray()
    }

    private fun findFirstTarget(nums: List<Int>, target: Int, startIngLo: Int, startingHi: Int): Int {
        var lo = startIngLo
        var hi = startingHi
        var mid: Int

        while (lo <= hi) {
            mid = (lo + hi) / 2
            if (nums[mid] != target) {
                lo = mid + 1
            } else if (mid == 0 || nums[mid - 1] != target) {
                return mid
            } else {
                hi = mid - 1
            }
        }
        return -1
    }

    private fun findLastTarget(nums: List<Int>, target: Int, startIngLo: Int, startingHi: Int): Int {
        var lo = startIngLo
        var hi = startingHi
        var mid: Int

        while (lo <= hi) {
            mid = (lo + hi) / 2
            if (nums[mid] != target) {
                hi = mid - 1
            } else if (mid == nums.size - 1 || nums[mid + 1] != target) {
                return mid
            } else {
                lo = mid + 1
            }
        }
        return -1
    }

    @Test
    fun `Returns the range of the elements`() {
        val res = searchRange(listOf(1, 2, 3, 4, 4, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9).toIntArray(), 7)
        Assertions.assertEquals(5, res[0])
        Assertions.assertEquals(8, res[1])
    }

    @Test
    fun `Empty list returns -1 -1`() {
        val res = searchRange(listOf<Int>().toIntArray(), 3)
        Assertions.assertEquals(-1, res[0])
        Assertions.assertEquals(-1, res[1])
    }

    @Test
    fun `Single elements returns 0 0`() {
        val res = searchRange(listOf(3).toIntArray(), 3)
        Assertions.assertEquals(0, res[0])
        Assertions.assertEquals(0, res[1])
    }

    @Test
    fun `Boundaries are correct`() {
        val res = searchRange(listOf(3, 3, 3, 3, 3, 3, 3).toIntArray(), 3)
        Assertions.assertEquals(res[0], 0)
        Assertions.assertEquals(res[1], 6)
    }
}