package binarysearch

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index.
If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞.
In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.
 */
class FindPeakElement {
    fun findPeakElement(nums: IntArray): Int {
        if(nums.size == 1) return 0

        var minIdx = 0
        var maxIdx = nums.size - 1
        var midIdx: Int

        while(minIdx <= maxIdx) {
            midIdx = (minIdx + maxIdx) / 2
            if(isPeak(nums, midIdx)) {
                return midIdx
            } else if(isRightIncreasing(nums, midIdx)) {
                minIdx = midIdx + 1
            } else {
                maxIdx = midIdx - 1
            }
        }

        return -1
    }

    private fun isPeak(nums: IntArray, idx: Int): Boolean {
        return idx == 0 && nums[idx + 1] < nums[idx]
                || idx == nums.size - 1 && nums[idx - 1] < nums[idx]
                || idx != 0 && idx != nums.size - 1 && nums[idx - 1] < nums[idx] && nums[idx + 1] < nums[idx]
    }

    private fun isRightIncreasing(nums: IntArray, idx: Int): Boolean {
        return idx != nums.size - 1 && nums[idx + 1] > nums[idx]
    }

    private fun isLeftIncreasing(nums: IntArray, idx: Int): Boolean {
        return idx != 0 && nums[idx - 1] > nums[idx]
    }

    @Test
    fun `finds peak element in (1,2,3,1)`() {
        val res = findPeakElement(listOf(1,2,3,1).toIntArray())
        Assertions.assertTrue(res == 2)
    }

    @Test
    fun `finds peak element in (1,2,1,3,5,6,4)`() {
        val res = findPeakElement(listOf(1,2,1,3,5,6,4).toIntArray())
        Assertions.assertTrue(res == 1 || res == 5)
    }
}