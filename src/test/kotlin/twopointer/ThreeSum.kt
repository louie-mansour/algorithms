package twopointer

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an integer array nums, return all the triplets
[nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.
 */
class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val sortedNums = nums.sorted()
        val memo = mutableSetOf<List<Int>>()
        var lo: Int
        var hi: Int
        for (i in 0 .. sortedNums.size - 2) {
            lo = i + 1
            hi = sortedNums.size - 1
            while (lo < hi) {
                if (sortedNums[i] + sortedNums[lo] + sortedNums[hi] < 0) {
                    lo++
                } else if (sortedNums[i] + sortedNums[lo] + sortedNums[hi] > 0) {
                    hi--
                } else {
                    memo.add(listOf(sortedNums[i], sortedNums[lo], sortedNums[hi]))
                    lo++
                    hi--
                }
            }
        }
        return memo.toList()
    }

    @Test
    fun `test (-1,0,1,2,-1,-4)`() {
        val res = threeSum(listOf(-1,0,1,2,-1,-4).toIntArray())
        Assertions.assertEquals(-1, res[0][0])
        Assertions.assertEquals(-1, res[0][1])
        Assertions.assertEquals(2, res[0][2])

        Assertions.assertEquals(-1, res[1][0])
        Assertions.assertEquals(0, res[1][1])
        Assertions.assertEquals(1, res[1][2])
    }

    @Test
    fun `test (0,1,1)`() {
        val res = threeSum(listOf(0,1,1).toIntArray())
        Assertions.assertTrue(res.isEmpty())
    }

    @Test
    fun `test (0,0,0)`() {
        val res = threeSum(listOf(0,0,0).toIntArray())
        Assertions.assertEquals(0, res[0][0])
        Assertions.assertEquals(0, res[0][1])
        Assertions.assertEquals(0, res[0][2])
    }
}