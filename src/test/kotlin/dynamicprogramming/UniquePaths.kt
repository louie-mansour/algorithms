package dynamicprogramming

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.
 */
class UniquePaths {
    fun uniquePaths(m: Int, n: Int): Int {
        val table = Array(m) { IntArray(n) }
        for(mIdx in 0 until m) {
            for (nIdx in 0 until n) {
                if(mIdx == 0 || nIdx == 0) {
                    table[mIdx][nIdx] = 1
                } else {
                    table[mIdx][nIdx] = table[mIdx - 1][nIdx] + table[mIdx][nIdx - 1]
                }
            }
        }
        return table[m - 1][n - 1]
    }

    @Test
    fun `m = 3, n = 7`() {
        val result = uniquePaths(3, 7)
        Assertions.assertEquals(28, result)
    }

    @Test
    fun `m = 3, n = 2`() {
        val result = uniquePaths(3, 2)
        Assertions.assertEquals(3, result)
    }
}