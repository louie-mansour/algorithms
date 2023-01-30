package binarysearch

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.
 */
class FindMinimumInRotatedSortedArray {
    fun findMin(nums: IntArray): Int {
        if (nums.isEmpty()) {
            throw RuntimeException("invalid input")
        }
        if (nums[0] < nums[nums.size - 1]) {
            return nums[0]
        }
        if (nums.size == 2) {
            if (nums[0] < nums[1]) {
                return nums[0]
            } else {
                return nums[1]
            }
        }

        var lo = 0
        var hi = nums.size - 1
        var mid: Int
        var lowestValue = Int.MAX_VALUE
        while (lo <= hi) {
            mid = (hi + lo) / 2
            if (nums[mid] < lowestValue) {
                lowestValue = nums[mid]
            }
            if (nums[mid] >= nums[0]) {
                lo = mid + 1
            } else {
                hi = mid - 1
            }
        }
        return lowestValue
    }

    @Test
    fun `find minimum when 1`() {
        val res = findMin(listOf(5,1,2,3,4).toIntArray())
        Assertions.assertEquals(1, res)
    }

    @Test
    fun `find minimum when 0`() {
        val res = findMin(listOf(4,5,6,7,0,1,2).toIntArray())
        Assertions.assertEquals(0, res)
    }

    @Test
    fun `find minimum when not rotated`() {
        val res = findMin(listOf(11,13,15,17).toIntArray())
        Assertions.assertEquals(11, res)
    }
}