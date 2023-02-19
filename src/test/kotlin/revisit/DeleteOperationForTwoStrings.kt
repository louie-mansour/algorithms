package revisit

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.
 */
class DeleteOperationForTwoStrings {
    fun minDistance(word1: String, word2: String): Int {
        val memo = Array(word1.length + 1) { IntArray(word2.length + 1)}
        for (i in 1..word1.length) {
            for (j in 1..word2.length) {
                if (word1[i - 1] == word2[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1
                } else {
                    memo[i][j] = maxOf(memo[i - 1][j], memo[i][j - 1])
                }
            }
        }
        return word1.length + word2.length - 2 * memo[word1.length][word2.length]
    }
    @Test
    fun `sea and eat`() {
        val result = minDistance("sea", "eat")
        Assertions.assertEquals(2, result)
    }

    @Test
    fun `leetcode and etco`() {
        val result = minDistance("leetcode", "etco")
        Assertions.assertEquals(4, result)
    }

    @Test
    fun `mart and karma`() {
        val result = minDistance("mart", "karma")
        Assertions.assertEquals(5, result)
    }
}