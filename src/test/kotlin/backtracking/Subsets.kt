package backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an integer array nums of unique elements, return all possible
subsets
 (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
 */
class Subsets {
    fun subsets(nums: IntArray): List<List<Int>> {
        val memo = mutableSetOf<Set<Int>>()
        subsets(emptyList(), nums.toList(), memo)
        return memo.map { it.toList() }
    }

    private fun subsets(prefix: List<Int>, remainingNums: List<Int>, memo: MutableSet<Set<Int>>) {
        memo.add(prefix.toSet())

        for(i in remainingNums.indices) {
            subsets(prefix + remainingNums[i], remainingNums.subList(i + 1, remainingNums.size), memo)
        }
    }

    @Test
    fun `powerset of (1, 2, 3)`() {
        val res = subsets(listOf(1, 2, 3).toIntArray())
        Assertions.assertTrue(
            isEqualSubsets(
                listOf(
                    listOf(),
                    listOf(1),
                    listOf(2),
                    listOf(1,2),
                    listOf(3),
                    listOf(1,3),
                    listOf(2,3),
                    listOf(1,2,3),
                ),
                res
            )
        )
    }

    private fun isEqualSubsets(expected: List<List<Int>>, actual: List<List<Int>>): Boolean {
        if (expected.size != actual.size) {
            return false
        }

        val expectedHashes = expected.map { it.toHashSet() }
        val actualHashes = actual.map { it.toHashSet() }
        for (actualHash in actualHashes) {
            if (!expectedHashes.contains(actualHash)) {
                return false
            }
        }
        return true
    }
}