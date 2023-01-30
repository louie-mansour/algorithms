package revisit

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.
 */
class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) {
            return emptyList()
        }

        val elements = mutableSetOf<Int>()
        val setsWhichEqualZero = mutableSetOf<List<Int>>()
        for (i in 0..nums.size - 2) {
            for (j in i + 1.. nums.size - 1) {
                elements.add(nums[i])
                if (elements.contains(-(nums[i] + nums[j]))) {
                    setsWhichEqualZero.add(listOf(nums[i], nums[j], -(nums[i] + nums[j])).sorted())
                }
            }
            elements.clear()
        }
        return setsWhichEqualZero.toList()
    }
    @Test
    fun `test 0`() {
        val res = threeSum(listOf(-1,0,1,2,-1,-4).toIntArray())
        Assertions.assertEquals(res.size, 2)

        Assertions.assertEquals(res[0][0], -1)
        Assertions.assertEquals(res[0][1], -1)
        Assertions.assertEquals(res[0][2], 2)

        Assertions.assertEquals(res[1][0], -1)
        Assertions.assertEquals(res[1][1], 0)
        Assertions.assertEquals(res[1][2], 1)
    }
}