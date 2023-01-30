package revisit

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.
 */
class SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) {
            return -1
        }
        if (nums.size == 1) {
           if (nums.size == target) {
               return 0
           }
            return -1
        }

        val pivot = findPivot(nums.toList())
        return findIndex(nums.toList(), pivot, target)
    }

    private fun findPivot(nums: List<Int>): Int {
        if (nums.size == 2) {
            return if(nums[0] <= nums[1]) {
                0
            } else {
                1
            }
        }
        var lo = 0
        var hi = nums.size - 1
        var mid: Int

        while (lo <= hi) {
            mid = (hi + lo) / 2

            if (mid == 0 || nums[mid -1] > nums[mid]) {
                return (nums.size - mid - 1) % nums.size
            } else if (nums[mid] > nums[nums.size - 1]) {
                lo = mid + 1
            } else {
                hi = mid - 1
            }
        }
        return -1
    }

    private fun findIndex(nums: List<Int>, pivot: Int, target: Int): Int {
        var lo = 0
        var hi = nums.size - 1
        var mid: Int

        while (lo <= hi) {
            mid = (hi + lo) / 2

            if (nums[transform(nums.size, pivot, mid)] == target) {
                return transform(nums.size, pivot, mid)
            } else if (nums[transform(nums.size, pivot, mid)] < target) {
                lo = mid + 1
            } else {
                hi = mid - 1
            }
        }
        return -1
    }

    private fun transform(numsSize: Int, pivot: Int, idx: Int): Int {
        return (idx + numsSize - pivot) % numsSize
    }

    @Test
    fun `Empty array return -1`() {
        Assertions.assertEquals(-1, search(listOf<Int>().toIntArray(), 1))
    }

    @Test
    fun `Single element array with target return 0`() {
        Assertions.assertEquals(0, search(listOf(1).toIntArray(), 1))
    }

    @Test
    fun `Single element array without target return -1`() {
        Assertions.assertEquals(-1, search(listOf(1).toIntArray(), 2))
    }

    @Test
    fun `No element in non-rotated array`() {
        Assertions.assertEquals(-1, search(listOf(1, 2, 3, 4, 5, 6, 7, 8).toIntArray(), 9))
    }

    @Test
    fun `Element in non-rotated array`() {
        Assertions.assertEquals(5, search(listOf(1, 2, 3, 4, 5, 6, 7, 8).toIntArray(), 6))
    }

    @Test
    fun `Element in non-rotated array top edge case`() {
        Assertions.assertEquals(7, search(listOf(1, 2, 3, 4, 5, 6, 7, 8).toIntArray(), 8))
    }

    @Test
    fun `Element in non-rotated array bottom edge case`() {
        Assertions.assertEquals(0, search(listOf(1, 2, 3, 4, 5, 6, 7, 8).toIntArray(), 1))
    }

    @Test
    fun `No element in rotated array`() {
        Assertions.assertEquals(-1 ,search(listOf(4, 5, 6, 7, 8, 1, 2, 3).toIntArray(), 9))
    }

    @Test
    fun `Element in rotated array`() {
        Assertions.assertEquals(1 ,search(listOf(4, 5, 6, 7, 8, 1, 2, 3).toIntArray(), 5))
    }

    @Test
    fun `Element in rotated array bottom edge case`() {
        Assertions.assertEquals(0 ,search(listOf(4, 5, 6, 7, 8, 1, 2, 3).toIntArray(), 4))
    }

    @Test
    fun `Element in rotated array top edge case`() {
        Assertions.assertEquals(7 ,search(listOf(4, 5, 6, 7, 8, 1, 2, 3).toIntArray(), 3))
    }

    @Test
    fun `Second element in two-element array`() {
        Assertions.assertEquals(1 ,search(listOf(3, 1).toIntArray(), 1))
    }

    @Test
    fun `middle element in a rotated 3 element array`() {
        Assertions.assertEquals(1 ,search(listOf(3, 5, 1).toIntArray(), 5))
    }

    @Test
    fun `another failed test`() {
        Assertions.assertEquals(3 ,search(listOf(7,8,1,2,3,4,5,6).toIntArray(), 2))
    }

    @Test
    fun `failed test`() {
        /*
        1,2,3,4,5
        5,1,2,3,4
        pivot = 4
         */
        Assertions.assertEquals(1 ,search(listOf(5,1,2,3,4).toIntArray(), 1))
    }
}