package backtracking/*
Given a collection of numbers, nums, that might contain duplicates,
return all possible unique permutations in any order.
 */

class PermutationsII {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val permutations = mutableSetOf<List<Int>>()
        findPermutations(listOf(), nums.toList(), permutations)
        return permutations.toList()
    }

    private fun findPermutations(prefix: List<Int>, suffix: List<Int>, permutations: MutableSet<List<Int>>) {
        if (suffix.isEmpty()) {
            permutations.add(prefix)
        }

        for (idx in suffix.indices) {
            findPermutations(
                prefix + suffix[idx],
                suffix.subList(0, idx) + suffix.subList(idx + 1, suffix.size),
                permutations
            )
        }
    }
}