package revisit

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

// https://leetcode.com/problems/longest-increasing-subsequence/solutions/74824/java-python-binary-search-o-nlogn-time-with-explanation/?envType=study-plan&id=algorithm-ii

class LongestIncreasingSubsequence {
    fun lengthOfLIS(nums: IntArray): Int {
        val tails = IntArray(nums.size)
        var size = 0
        for (x in nums) {
            var i = 0
            var j = size
            while (i != j) {
                val m = (i + j) / 2
                if (tails[m] < x) i = m + 1 else j = m
            }
            tails[i] = x
            if (i == size) ++size
        }
        return size
    }

    @Test
    fun `(10,9,2,5,3,7,101,18)`() {
        val result = lengthOfLIS(listOf(10,9,2,5,3,7,101,18).toIntArray())
        Assertions.assertEquals(4, result)
    }

    @Test
    fun `(0,1,0,3,2,3)`() {
        val result = lengthOfLIS(listOf(0,1,0,3,2,3).toIntArray())
        Assertions.assertEquals(4, result)
    }

    @Test
    fun `(7,7,7,7,7,7,7)`() {
        val result = lengthOfLIS(listOf(7,7,7,7,7,7,7).toIntArray())
        Assertions.assertEquals(1, result)
    }
}