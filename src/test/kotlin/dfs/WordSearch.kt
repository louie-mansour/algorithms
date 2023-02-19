package dfs

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.
 */
class WordSearch {
    fun exist(board: Array<CharArray>, word: String): Boolean {
        if (board.isEmpty() || board[0].isEmpty()) {
            return false
        }

        val boardMap = board.flatMap { it.toList() }.groupBy { it }.map { Pair(it.key, it.value.size) }.toMap()
        val wordMap = word.groupBy { it }.map { Pair(it.key, it.value.size) }.toMap()

        for (char in wordMap) {
            if (char.value > (boardMap[char.key] ?: 0)) {
                return false
            }
        }

        for(i in board.indices) {
            for (j in board[0].indices) {
                if (dfs(board, word, i, j, setOf())) {
                    return true
                }
            }
        }
        return false
    }

    private fun dfs(board: Array<CharArray>, word: String, i: Int, j: Int, visited: Set<Pair<Int, Int>>): Boolean {
        if (word.isEmpty()) {
            return true
        }
        if (i < 0 || i >= board.size || j < 0 || j >= board[0].size) {
            return false
        }
        if (board[i][j] != word[0]) {
            return false
        }
        if (visited.contains(Pair(i, j))) {
            return false
        }

        return dfs(board, word.substring(1, word.length), i + 1, j, visited + Pair(i, j))
                || dfs(board, word.substring(1, word.length), i, j + 1, visited + Pair(i, j))
                || dfs(board, word.substring(1, word.length), i - 1, j, visited + Pair(i, j))
                || dfs(board, word.substring(1, word.length), i, j - 1, visited + Pair(i, j))
    }

    @Test
    fun `listOf(listOf('A','B','C','E'),listOf('S','F','C','S'),listOf('A','D','E','E')) and ABCCED returns true`() {
        Assertions.assertTrue(exist(
            listOf(
                listOf('A','B','C','E'),
                listOf('S','F','C','S'),
                listOf('A','D','E','E'),
            ).map { it.toCharArray() }.toTypedArray(), "ABCCED")
        )
    }

    @Test
    fun `listOf(listOf('A','B','C','E'),listOf('S','F','C','S'),listOf('A','D','E','E')) and ABCB returns false`() {
        Assertions.assertFalse(exist(
            listOf(
                listOf('A','B','C','E'),
                listOf('S','F','C','S'),
                listOf('A','D','E','E'),
            ).map { it.toCharArray() }.toTypedArray(), "ABCB")
        )
    }
}