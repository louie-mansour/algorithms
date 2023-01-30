package revisit

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given two strings s and p, return an array of all the start indices of p's anagrams in s.
You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.
 */

class FindAllAnagramsInAString {
    fun findAnagrams(s: String, p: String): List<Int> {
        // I've solved this before, but when I tried again I made the p hashtable more than once which was too slow
        // See if I can do it again only generating the hash table once
        // (note that it's possible to check if 2 maps are equal)
    }
    @Test
    fun `Find anagrams of second string in first string`() {
        val res = findAnagrams("cbaebabacd", "abc")
        Assertions.assertEquals(res.size, 2)
        Assertions.assertEquals(res[0], 0)
        Assertions.assertEquals(res[1], 6)
    }

    @Test
    fun `Can handle overlapping anagrams`() {
        val res = findAnagrams("abab", "ab")
        Assertions.assertEquals(res.size, 3)
        Assertions.assertEquals(res[0], 0)
        Assertions.assertEquals(res[1], 1)
        Assertions.assertEquals(res[2], 2)
    }

    @Test
    fun `Empty first string results in no anagrams`() {
        val res = findAnagrams("", "ab")
        Assertions.assertTrue(res.isEmpty())
    }

    @Test
    fun `Empty second string results in no anagrams`() {
        val res = findAnagrams("cbaebabacd", "")
        Assertions.assertTrue(res.isEmpty())
    }
}