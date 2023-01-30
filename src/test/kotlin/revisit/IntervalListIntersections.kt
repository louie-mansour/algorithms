package revisit

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

SOLUTION TO STUDY:
https://leetcode.com/problems/interval-list-intersections/solutions/647482/python-two-pointer-approach-thinking-process-diagrams/?envType=study-plan&id=algorithm-ii
 */

class IntervalListIntersections {
    fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
        if (firstList.isEmpty() || secondList.isEmpty()) {
            return listOf<List<Int>>().map { it.toIntArray() }.toTypedArray()
        }
        val min = maxOf(firstList[0][0], secondList[0][0])
        val max = minOf(firstList[firstList.size - 1][1], secondList[secondList.size - 1][0])

        val firstMap = buildSet(firstList)
        val secondSet = buildSet(secondList)

        val intersection = mutableListOf<List<Int>>()
        var inInterval = false
        var currentInterval = mutableListOf<Int>()
        for(i in min..max + 1) {
            if (firstMap.contains(i) && secondSet.contains(i) ) {
                if (inInterval) {
                    continue
                } else {
                    currentInterval = mutableListOf(i)
                    inInterval = true
                }
            } else {
                if (inInterval) {
                    currentInterval.add(i - 1)
                    intersection.add(currentInterval)
                    inInterval = false
                } else {
                    continue
                }
            }
        }
        return intersection.map { it.toIntArray() }.toTypedArray()
    }

    private fun buildSet(list: Array<IntArray>): Map<Int, Boolean> {
        val map = mutableMapOf<Int, Boolean>()
        for(interval in list) {
            for(i in interval[0]..interval[1]) {
                map[i] = i == interval[0] || i == interval[1]
            }
        }
        return map
    }

    @Test
    fun `Returns the intersection of input lists`() {
        val res = intervalIntersection(
            listOf(
                listOf(0, 1),
                listOf(4, 5),
                listOf(7, 10),
                listOf(15, 25),
                listOf(26, 28),
                listOf(48, 50),
            ).map { it.toIntArray() }.toTypedArray(),
            listOf(
                listOf(0, 4),
                listOf(10, 20),
                listOf(23, 37),
                listOf(40, 42),
            ).map { it.toIntArray() }.toTypedArray(),
        )
        Assertions.assertEquals(res[0][0], 0)
        Assertions.assertEquals(res[0][1], 1)

        Assertions.assertEquals(res[1][0], 4)
        Assertions.assertEquals(res[1][1], 4)

        Assertions.assertEquals(res[2][0], 10)
        Assertions.assertEquals(res[2][1], 10)

        Assertions.assertEquals(res[3][0], 15)
        Assertions.assertEquals(res[3][1], 20)

        Assertions.assertEquals(res[4][0], 23)
        Assertions.assertEquals(res[4][1], 28)
    }
}