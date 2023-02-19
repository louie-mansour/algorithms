package dynamicprogramming

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

/*
Given an integer array nums, return the length of the longest strictly increasing subsequence
 */
class LongestIncreasingSubsequence {
    fun longestIncreasingSubsequence(nums: List<Int>): Int {
        if (nums.isEmpty()) {
            return 0
        }
        var maxTailSize = 0
        val tails = IntArray(nums.size) { Int.MAX_VALUE }
        tails[0] = nums[0]
        for (num in nums) {
            var tailIdx = 0
            while (num > tails[tailIdx]) {
                tailIdx++
            }
            tails[tailIdx] = num
            if (tailIdx + 1 > maxTailSize) {
                maxTailSize = tailIdx + 1
            }
        }
        return maxTailSize
    }

    @Test
    fun `listOf(1, 2, 3, 6, 7, 9, 0) returns 6`() {
        val result = longestIncreasingSubsequence(listOf(1, 2, 3, 6, 7, 9, 0))
        Assertions.assertEquals(6, result)
    }

    @Test
    fun `listOf(1, 8, 9, 3, 4, 5, 6) return 5`() {
        val result = longestIncreasingSubsequence(listOf(1, 8, 9, 3, 4, 5, 6))
        Assertions.assertEquals(5, result)
    }

    @Test
    fun `listOf() return 0`() {
        val result = longestIncreasingSubsequence(listOf())
        Assertions.assertEquals(0, result)
    }
}