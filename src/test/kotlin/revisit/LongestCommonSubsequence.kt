package revisit/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.
 */
class LongestCommonSubsequence {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dpTable = Array(text1.length + 1) { IntArray(text2.length + 1) }

        for (i in 1..text1.length) {
            for (j in 1..text2.length) {
                if (text1[i - 1] == text2[j - 1]) {
                    dpTable[i][j] = dpTable[i - 1][j - 1] + 1
                } else {
                    dpTable[i][j] = maxOf(dpTable[i - 1][j], dpTable[i][j - 1])
                }
            }
        }
        return dpTable[text1.length][text2.length]
    }
}