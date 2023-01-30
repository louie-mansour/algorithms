package dfs

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
 */
class NumberOfIslands {
    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty() || grid[0].isEmpty()) {
            return 0
        }
        
        val visitedNodes = mutableSetOf<Pair<Int, Int>>()
        var numberOfIslands = 0
        for (rowNumber in grid.indices) {
            for (colNumber in grid[rowNumber].indices) {
                if (visitedNodes.contains(Pair(rowNumber, colNumber))) {
                    continue
                }
                if (grid[rowNumber][colNumber] == '0') {
                    visitedNodes.add(Pair(rowNumber, colNumber))
                    continue
                }
                searchIsland(grid, rowNumber, colNumber, visitedNodes)
                numberOfIslands++
            }
        }
        return numberOfIslands
    }

    private fun searchIsland(grid: Array<CharArray>, rowNumber: Int, colNumber: Int, visitedNodes: MutableSet<Pair<Int, Int>>) {
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addLast(Pair(rowNumber, colNumber))

        while (stack.isNotEmpty()) {
            val position = stack.removeLast()
            if (position.first == -1 || position.second == -1 || position.first == grid.size || position.second == grid[0].size) {
                continue
            }
            if (grid[position.first][position.second] == '0') {
                continue
            }
            if (visitedNodes.contains(position)) {
                continue
            }
            visitedNodes.add(position)
            stack.addLast(Pair(position.first - 1, position.second))
            stack.addLast(Pair(position.first + 1, position.second))
            stack.addLast(Pair(position.first, position.second - 1))
            stack.addLast(Pair(position.first, position.second + 1))
        }
    }

    @Test
    fun `Counts number of islands`() {
        val grid = listOf(
            listOf('1', '1', '0', '0', '0', '0'),
            listOf('1', '1', '0', '0', '0', '0'),
            listOf('0', '0', '0', '1', '1', '1'),
            listOf('0', '1', '0', '0', '0', '1'),
            listOf('1', '0', '1', '1', '0', '1'),
            listOf('0', '0', '0', '1', '0', '1'),
            listOf('0', '0', '0', '1', '0', '0'),
            listOf('1', '1', '0', '1', '1', '1'),
        ).map { it.toCharArray() }.toTypedArray()

        val res = numIslands(grid)
        Assertions.assertEquals(6, res)
    }

    @Test
    fun `One large island`() {
        val grid = listOf(
            listOf('1', '1', '1', '1', '1', '1'),
            listOf('1', '1', '1', '1', '1', '1'),
            listOf('1', '1', '1', '1', '1', '1'),
            listOf('1', '1', '1', '1', '1', '1'),
            listOf('1', '1', '1', '1', '1', '1'),
            listOf('1', '1', '1', '1', '1', '1'),
            listOf('1', '1', '1', '1', '1', '1'),
            listOf('1', '1', '1', '1', '1', '1'),
        ).map { it.toCharArray() }.toTypedArray()

        val res = numIslands(grid)
        Assertions.assertEquals(1, res)
    }

    @Test
    fun `No islands`() {
        val grid = listOf(
            listOf('0', '0', '0', '0', '0', '0'),
            listOf('0', '0', '0', '0', '0', '0'),
            listOf('0', '0', '0', '0', '0', '0'),
            listOf('0', '0', '0', '0', '0', '0'),
            listOf('0', '0', '0', '0', '0', '0'),
            listOf('0', '0', '0', '0', '0', '0'),
            listOf('0', '0', '0', '0', '0', '0'),
            listOf('0', '0', '0', '0', '0', '0'),
        ).map { it.toCharArray() }.toTypedArray()

        val res = numIslands(grid)
        Assertions.assertEquals(0, res)
    }

    @Test
    fun `No rows`() {
        val grid = listOf(
            listOf<Char>()
        ).map { it.toCharArray() }.toTypedArray()

        val res = numIslands(grid)
        Assertions.assertEquals(0, res)
    }

    @Test
    fun `One row`() {
        val grid = listOf(
            listOf('1', '1', '0', '1', '0', '1'),
        ).map { it.toCharArray() }.toTypedArray()

        val res = numIslands(grid)
        Assertions.assertEquals(3, res)
    }

    @Test
    fun `One column`() {
        val grid = listOf(
            listOf('1'),
            listOf('1'),
            listOf('0'),
            listOf('0'),
            listOf('1'),
            listOf('0'),
            listOf('0'),
            listOf('1'),
            listOf('0'),
            listOf('1'),
        ).map { it.toCharArray() }.toTypedArray()

        val res = numIslands(grid)
        Assertions.assertEquals(4, res)
    }

    @Test
    fun `I shaped island`() {
        val grid = listOf(
            listOf('1', '1', '1'),
            listOf('0', '1', '0'),
            listOf('1', '1', '1'),
        ).map { it.toCharArray() }.toTypedArray()

        val res = numIslands(grid)
        Assertions.assertEquals(1, res)
    }
}