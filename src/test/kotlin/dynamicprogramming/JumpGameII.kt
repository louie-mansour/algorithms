package dynamicprogramming

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i.
In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 */
class JumpGameII {
    fun jump(nums: IntArray): Int {
        val jumpsTable = mutableMapOf(Pair(0, 0))
        for(currentPosition in nums.indices) {

            val maxJumpSize = nums[currentPosition]
            for (jumpSize in 1..maxJumpSize) {
                val jumpPosition = currentPosition + jumpSize
                if (!jumpsTable.contains(jumpPosition)) {
                    jumpsTable[jumpPosition] = jumpsTable[currentPosition]!! + 1
                } else {
                    jumpsTable[jumpPosition] = minOf(jumpsTable[currentPosition]!! + 1, jumpsTable[jumpPosition]!!)
                }
            }
        }
        return jumpsTable[nums.size - 1]!!
    }

    @Test
    fun `2,3,1,1,4`() {
        val result = jump(listOf(2,3,1,1,4).toIntArray())
        Assertions.assertEquals(2, result)
    }

    @Test
    fun `2,3,0,1,4`() {
        val result = jump(listOf(2,3,0,1,4).toIntArray())
        Assertions.assertEquals(2, result)
    }
}