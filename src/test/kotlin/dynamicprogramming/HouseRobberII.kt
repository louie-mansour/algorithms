package dynamicprogramming

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HouseRobberII {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) {
            return 0
        }
        if (nums.size == 1) {
            return nums[0]
        }
        if (nums.size == 2) {
            return maxOf(nums[0], nums[1])
        }
        if (nums.size == 3) {
            return maxOf(nums[0], nums[1], nums[2])
        }
        val table0 = mutableMapOf<Int, Int>()
        val table1 = mutableMapOf<Int, Int>()

        robHouses(nums, 0, nums.size - 2, table0)
        robHouses(nums, 1, nums.size - 1, table1)

        return maxOf(maxOf(table0[nums.size - 2]!!, table0[nums.size - 3]!!), maxOf(table1[nums.size - 1]!!, table1[nums.size - 2]!!))
    }

    private fun robHouses(nums: IntArray, startingPosition: Int, endingPosition: Int, table: MutableMap<Int, Int>) {
        for (idx in startingPosition..endingPosition) {
            if (idx - 3 < startingPosition) {
                if (idx - 2 == startingPosition) {
                    table[idx] = nums[idx] + table[idx - 2]!!
                    continue
                }
                table[idx] = nums[idx]
                continue
            }
            table[idx] = nums[idx] + maxOf(table[idx - 2]!!, table[idx - 3]!!)
        }
    }

    @Test
    fun `robbing (2,3,2)`() {
        val res = rob(listOf(2, 3, 2).toIntArray())
        Assertions.assertEquals(3, res)
    }

    @Test
    fun `robbing (1,2,3,1)`() {
        val res = rob(listOf(1, 2, 3, 1).toIntArray())
        Assertions.assertEquals(4, res)
    }

    @Test
    fun `robbing (200,3,140,20,10)`() {
        val res = rob(listOf(200,3,140,20,10).toIntArray())
        Assertions.assertEquals(340, res)
    }

    @Test
    fun `robbing (1,3,1,3,100)`() {
        val res = rob(listOf(1,3,1,3,100).toIntArray())
        Assertions.assertEquals(103, res)
    }
}