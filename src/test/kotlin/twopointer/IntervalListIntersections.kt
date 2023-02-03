package twopointer

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
You are given two lists of closed intervals, firstList and secondList,
where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 */
class IntervalListIntersections {
    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        var firstIter = 0
        var secondIter = 0
        val intersections = mutableListOf<List<Int>>()
        var firstMin: Int
        var firstMax: Int
        var secondMin: Int
        var secondMax: Int
        while(firstIter < firstList.size && secondIter < secondList.size) {
            firstMin = firstList[firstIter][0]
            firstMax = firstList[firstIter][1]
            secondMin = secondList[secondIter][0]
            secondMax = secondList[secondIter][1]

            if (!isOverlap(firstMin, firstMax, secondMin, secondMax)) {
                if (firstMax < secondMin) {
                    firstIter++
                    continue
                } else {
                    secondIter++
                    continue
                }
            }
            intersections.add(listOf(maxOf(firstMin, secondMin), minOf(firstMax, secondMax)))
            if (firstMax < secondMax) {
                firstIter++
            } else {
                secondIter++
            }
        }
        return intersections.map { it.toIntArray() }.toTypedArray()
    }

    private fun isOverlap(first0: Int, first1: Int, second0: Int, second1: Int): Boolean {
        return first0 in second0..second1 || second0 in first0..first1
    }

    @Test
    fun `((0,2),(5,10),(13,23),(24,25)) and ((1,5),(8,12),(15,24),(25,26))`() {
        val result = intervalIntersection(
            listOf(listOf(0,2),listOf(5,10),listOf(13,23),listOf(24,25)).map { it.toIntArray() }.toTypedArray(),
            listOf(listOf(1,5),listOf(8,12),listOf(15,24),listOf(25,26)).map { it.toIntArray() }.toTypedArray(),
        )

        Assertions.assertEquals(1, result[0][0])
        Assertions.assertEquals(2, result[0][1])

        Assertions.assertEquals(5, result[1][0])
        Assertions.assertEquals(5, result[1][1])

        Assertions.assertEquals(8, result[2][0])
        Assertions.assertEquals(10, result[2][1])

        Assertions.assertEquals(15, result[3][0])
        Assertions.assertEquals(23, result[3][1])

        Assertions.assertEquals(24, result[4][0])
        Assertions.assertEquals(24, result[4][1])

        Assertions.assertEquals(25, result[5][0])
        Assertions.assertEquals(25, result[5][1])
    }
}