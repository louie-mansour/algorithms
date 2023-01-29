package binarysearch

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
You are given an m x n integer matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.
 */
class SearchA2DMatrix {

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return false
        }

        val rowIndex = findRowIndex(matrix, target)
        if (rowIndex ==  -1) {
            return false
        }
        return isElementInRow(matrix[rowIndex], target)
    }

    private fun findRowIndex(matrix: Array<IntArray>, target: Int): Int {
        var lo = 0
        var hi = matrix.size - 1
        var mid: Int

        while(lo <= hi) {
            mid = (lo + hi) / 2
            if (matrix[mid][0] > target) {
                hi = mid - 1
            } else if (matrix[mid][matrix[0].size - 1] < target) {
                lo = mid + 1
            } else {
                return mid
            }
        }
        return -1
    }

    private fun isElementInRow(row: IntArray, target: Int): Boolean {
        var lo = 0
        var hi = row.size - 1
        var mid: Int

        while (lo <= hi) {
            mid = (lo + hi) / 2
            if (row[mid] > target) {
                hi = mid - 1
            } else if (row[mid] < target) {
                lo = mid + 1
            } else {
                return true
            }
        }
        return false
    }

    @Test
    fun `Returns true when the element exists in the matrix`() {
        Assertions.assertTrue(
            searchMatrix(
                listOf(
                    listOf(1, 3, 5, 7),
                    listOf(8, 9, 10, 22),
                    listOf(45, 47, 48, 49),
                ).map { it.toIntArray() }.toTypedArray(),
                22),
        )
    }

    @Test
    fun `Returns false when the element doesn't exists in the matrix`() {
        Assertions.assertFalse(
            searchMatrix(
                listOf(
                    listOf(1, 3, 5, 7),
                    listOf(8, 9, 10, 22),
                    listOf(45, 47, 48, 49),
                ).map { it.toIntArray() }.toTypedArray(),
                46),
        )
    }
}