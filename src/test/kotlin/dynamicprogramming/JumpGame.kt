package dynamicprogramming/*
You are given an integer array nums. You are initially positioned at the array's first index,
and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.
 */
class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        val memo = BooleanArray(nums.size)
        dpJump(nums, 0, memo)
        return memo[nums.size - 1]
    }

    private fun dpJump(nums: IntArray, position: Int, memo: BooleanArray) {
        if (position >= memo.size || memo[position]) {
            return
        }
        memo[position] = true
        for (jumpLength in 1..nums[position]) {
            dpJump(nums, position + jumpLength, memo)
        }
    }
}