package hashlookup

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Solution {

    fun twoSum(nums: List<Int>, target: Int): Boolean {
        val memo = mutableSetOf<Int>();

        for (num in nums) {
            if (memo.contains(target - num)) {
                return true
            }
            memo.add(num)
        }
        return false
    }

    @Test
    fun `returns true when it exists`() {
        Assertions.assertTrue(twoSum(listOf(1, 5, 8, 4, 2, 6, 4545, 6, 3), 4550))
    }

    @Test
    fun `returns false when it doesn't exists`() {
        Assertions.assertTrue(twoSum(listOf(1, 5, 8, 4, 2, 6, 4545, 6, 3), 4550))
    }
}



