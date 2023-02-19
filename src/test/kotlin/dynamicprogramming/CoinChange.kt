package dynamicprogramming

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.
 */
class CoinChange {
    fun coinChange(const: IntArray, amount: Int): Int {
        val memo = IntArray(amount + 1)
        for (idx in memo.indices) {
            if (idx == 0) {
                continue
            }
            val options = mutableListOf<Int>()
            for (coinValue in const) {
                if (idx >= coinValue && memo[idx - coinValue] != -1) {
                    options.add(memo[idx - coinValue] + 1)
                }
            }

            if (options.isEmpty()) {
                memo[idx] = -1
            } else {
                memo[idx] = options.min()!!
            }
        }
        return memo.last()
    }

    @Test
    fun `1, 2, 5 to make 11`() {
        val result = coinChange(listOf(1, 2, 5).toIntArray(), 11)
        Assertions.assertEquals(3, result)
    }

    @Test
    fun `2 to make 3`() {
        val result = coinChange(listOf(2).toIntArray(), 3)
        Assertions.assertEquals(-1, result)
    }
    @Test
    fun `1 to make 0`() {
        val result = coinChange(listOf(1).toIntArray(), 0)
        Assertions.assertEquals(0, result)
    }
}