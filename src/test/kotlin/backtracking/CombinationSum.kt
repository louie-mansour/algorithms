package backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an array of distinct integers candidates and a target integer target,
return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 */

class CombinationSum {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val combinations = mutableSetOf<List<Int>>()
        findCombinations(emptyList(), candidates.toList(), target, combinations)
        return combinations.toList()
    }

    private fun findCombinations(combination: List<Int>, candidates: List<Int>, target: Int, sets: MutableSet<List<Int>>) {
        if (combination.sum() > target) {
            return
        }
        if (combination.sum() == target) {
            sets.add(combination.sorted())
            return
        }

        for(candidate in candidates) {
            findCombinations(combination + candidate, candidates, target, sets)
        }
    }

    @Test
    fun `test 0`() {
        val res = combinationSum(listOf(2,3,6,7).toIntArray(), 7)
        Assertions.assertEquals(res.size, 2)
        Assertions.assertEquals(res[0].filter { it == 2 }.size, 2)
        Assertions.assertEquals(res[0].filter { it == 3 }.size, 1)

        Assertions.assertEquals(res[1].filter { it == 7 }.size, 1)
    }

    @Test
    fun `test 1`() {
        val res = combinationSum(listOf(2,3,5).toIntArray(), 8)
        Assertions.assertEquals(res.size, 3)
        Assertions.assertEquals(res[0].filter { it == 2 }.size, 4)

        Assertions.assertEquals(res[1].filter { it == 2 }.size, 1)
        Assertions.assertEquals(res[1].filter { it == 3 }.size, 2)

        Assertions.assertEquals(res[2].filter { it == 3 }.size, 1)
        Assertions.assertEquals(res[2].filter { it == 5 }.size, 1)
    }
}