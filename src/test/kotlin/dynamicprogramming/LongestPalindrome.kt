package dynamicprogramming

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LongestPalindrome {
    fun longestPalindrome(s: String): String {
        val memo = Array(s.length) { BooleanArray(s.length)}
        s.forEachIndexed { idx, _ -> memo[idx][idx] = true }
        var longestPalindrome = s[0].toString()
        for (endIdx in 1 until s.length) {
            for (startIdx in 0 until endIdx) {
                if (s[startIdx] != s[endIdx]) {
                    memo[startIdx][endIdx] = false
                    continue
                }

                if (endIdx - startIdx == 1 || memo[startIdx + 1][endIdx - 1]) {
                    memo[startIdx][endIdx] = true
                }
                if (memo[startIdx][endIdx] && endIdx - startIdx + 1 > longestPalindrome.length) {
                    longestPalindrome = s.substring(startIdx, endIdx + 1)
                }
            }
        }
        return longestPalindrome
    }

    @Test
    fun `cbbc`() {
        val result = longestPalindrome("cbbc")
        Assertions.assertEquals("cbbc", result)
    }

    @Test
    fun `babad`() {
        val result = longestPalindrome("babad")
        Assertions.assertEquals("bab", result)
    }

    @Test
    fun `cbbd`() {
        val result = longestPalindrome("cbbd")
        Assertions.assertEquals("bb", result)
    }
}