package backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an integer array nums that may contain duplicates, return all possible
subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
 */
class SubsetsII {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val subsets = mutableSetOf<List<Int>>()
        findSubsets(emptyList(), nums.toList(), subsets)
        return subsets.map { it.toList() }.toList()
    }

    private fun findSubsets(prefix: List<Int>, suffix: List<Int>, subsets: MutableSet<List<Int>>) {
        if (!subsets.contains(prefix.sorted())) {
            subsets.add(prefix.sorted())
        }

        for (idx in suffix.indices) {
            findSubsets(prefix + suffix[idx], suffix.subList(idx + 1, suffix.size), subsets)
        }
    }

    @Test
    fun `(1,2,2)`() {
        val result = subsetsWithDup(listOf(1, 2, 2).toIntArray())
        Assertions.assertTrue(
            isEqual(
                listOf(
                    emptyList(),
                    listOf(1),
                    listOf(1, 2),
                    listOf(1, 2, 2),
                    listOf(2),
                    listOf(2, 2),
                ),
                result,
            )
        )
    }

    @Test
    fun `(0)`() {
        val result = subsetsWithDup(listOf(0).toIntArray())
        Assertions.assertTrue(
            isEqual(
                listOf(
                    emptyList(),
                    listOf(0),
                ),
                result,
            )
        )
    }

    private fun isEqual(actual: List<List<Int>>, expected: List<List<Int>>): Boolean {
        if (actual.size != expected.size) {
            return false
        }

        for (i in actual.indices) {
            if (actual[i].size != expected[i].size) {
                return false
            }
            for (j in actual[i].indices) {
                if (actual[i][j] != expected[i][j]) {
                    return false
                }
            }
        }
        return true
    }
}