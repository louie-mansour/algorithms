package dfs

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
There are n cities. Some of them are connected, while some are not.
If city a is connected directly with city b, and city b is connected directly with city c,
then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.
 */
class NumberOfProvinces {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        var numberOfProvinces = 0
        val visitedCities = mutableSetOf<Int>()
        for (cityIndex in isConnected.indices) {
            if (visitedCities.contains(cityIndex)) {
                continue
            }
            dfs(isConnected, cityIndex, visitedCities)
            numberOfProvinces++
        }
        return numberOfProvinces
    }

    private fun dfs(grid: Array<IntArray>, cityIdx: Int, visitedCities: MutableSet<Int>) {
        val stack = ArrayDeque<Int>()
        stack.addLast(cityIdx)
        while (stack.isNotEmpty()) {
            val currentCityIdx = stack.removeLast()
            if (visitedCities.contains(currentCityIdx)) {
                continue
            }
            for (connectionIdx in grid[currentCityIdx].indices) {
                if (grid[currentCityIdx][connectionIdx] == 1) {
                    stack.addLast(connectionIdx)
                }
            }
            visitedCities.add(currentCityIdx)
        }
    }

    @Test
    fun `two provinces`() {
        val res = findCircleNum(
            listOf(
                listOf(1,1,0),
                listOf(1,1,0),
                listOf(0,0,1)
            ).map { it.toIntArray() }.toTypedArray())

        Assertions.assertEquals(2, res)
    }

    @Test
    fun `three provinces`() {
        val res = findCircleNum(
            listOf(
                listOf(1,0,0),
                listOf(0,1,0),
                listOf(0,0,1)
            ).map { it.toIntArray() }.toTypedArray())

        Assertions.assertEquals(3, res)
    }

    @Test
    fun `four provinces`() {
        val res = findCircleNum(
            listOf(
                listOf(1,0,0,1),
                listOf(0,1,1,0),
                listOf(0,1,1,1),
                listOf(1,0,1,1),
            ).map { it.toIntArray() }.toTypedArray())

        Assertions.assertEquals(1, res)
    }
}