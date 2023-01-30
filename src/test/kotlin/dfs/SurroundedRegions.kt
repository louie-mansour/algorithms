package dfs

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
class SurroundedRegions {
    fun solve(board: Array<CharArray>): Unit {
        if (board.isEmpty() || board[0].isEmpty()) {
            return
        }

        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (visited.contains(Pair(i, j))) {
                    continue
                }
                if (board[i][j] == 'X') {
                    visited.add(Pair(i, j))
                    continue
                }
                dfs(board, i, j, visited)
            }
        }
    }

    private fun dfs(board: Array<CharArray>, i: Int, j: Int, visited: MutableSet<Pair<Int, Int>>) {
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addLast(Pair(i, j))

        val dfsVisited = mutableSetOf<Pair<Int, Int>>()

        while (stack.isNotEmpty()) {
            val current = stack.removeLast()
            if (current.first < 0 || current.second < 0 || current.first > board.size - 1 || current.second > board[0].size - 1) {
                continue
            }
            if (dfsVisited.contains(Pair(current.first, current.second))) {
                continue
            }
            if (board[current.first][current.second] != 'O') {
                continue
            }

            stack.addLast(Pair(current.first + 1, current.second))
            stack.addLast(Pair(current.first, current.second + 1))
            stack.addLast(Pair(current.first - 1, current.second))
            stack.addLast(Pair(current.first, current.second - 1))

            dfsVisited.add(Pair(current.first, current.second))
        }

        if (dfsVisited.any { it.first == 0 || it.second == 0 || it.first == board.size - 1 || it.second == board[0].size - 1 }) {
            visited.addAll(dfsVisited)
        } else {
            dfsVisited.forEach { board[it.first][it.second] = 'X' }
            visited.addAll(dfsVisited)
        }
    }

    @Test
    fun `test 0`() {
        val actual =
            listOf(
                listOf('X','X','X','X'),
                listOf('X','O','O','X'),
                listOf('X','X','O','X'),
                listOf('X','O','X','X'),
            ).map { it.toCharArray() }.toTypedArray()

        val expected =
            listOf(
                listOf('X','X','X','X'),
                listOf('X','X','X','X'),
                listOf('X','X','X','X'),
                listOf('X','O','X','X'),
            ).map { it.toCharArray() }.toTypedArray()
        solve(actual)

        Assertions.assertTrue(
            isEquivalentBoard(expected, actual)
        )
    }

    @Test
    fun `test 1`() {
        val actual =
            listOf(
                listOf('X'),
            ).map { it.toCharArray() }.toTypedArray()

        val expected =
            listOf(
                listOf('X'),
            ).map { it.toCharArray() }.toTypedArray()
        solve(actual)

        Assertions.assertTrue(
            isEquivalentBoard(expected, actual)
        )
    }

    private fun isEquivalentBoard(board0: Array<CharArray>, board1: Array<CharArray>): Boolean {
        if (board0.size != board1.size || board0.getOrNull(0)?.size != board1.getOrNull(0)?.size) {
            return false
        }

        for (i in board0.indices) {
            for (j in board0[i].indices) {
                if (board0[i][j] != board1[i][j]) {
                    return false
                }
            }
        }
        return true
    }
}