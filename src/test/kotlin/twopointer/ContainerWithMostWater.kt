package twopointer

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
You are given an integer array height of length n.
There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.
 */
class ContainerWithMostWater {
    fun maxArea(height: IntArray): Int {
        if (height.isEmpty()) {
            return 0
        }
        var hi = height.size - 1
        var lo = 0

        var maxArea = 0
        while (hi > lo) {
            val area = (hi - lo) * minOf(height[hi], height[lo])
            if (area > maxArea) {
                maxArea = area
            }

            if (height[hi] > height[lo]) {
                lo++
            } else {
                hi--
            }
        }
        return maxArea
    }

    @Test
    fun `Finds max area of water`() {
        val res = maxArea(listOf(1,8,6,2,5,4,8,3,7).toIntArray())
        Assertions.assertEquals(res, 49)
    }

    @Test
    fun `Finds max area of water in simpler case`() {
        val res = maxArea(listOf(1,1).toIntArray())
        Assertions.assertEquals(res, 1)
    }
}