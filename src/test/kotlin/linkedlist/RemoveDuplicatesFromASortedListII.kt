package linkedlist

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
leaving only distinct numbers from the original list. Return the linked list sorted as well.
 */

class RemoveDuplicatesFromASortedListII {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var newHead = head
        val dummy = ListNode(0)
        dummy.next = newHead
        var prev: ListNode? = dummy
        while (newHead != null) {
            if (newHead.next != null && newHead.`val` == newHead.next!!.`val`) {
                while (newHead!!.next != null && newHead.`val` == newHead.next!!.`val`) {
                    newHead = newHead.next
                }
                prev!!.next = newHead.next
            } else {
                prev = prev!!.next
            }
            newHead = newHead.next
        }
        return dummy.next
    }

    @Test
    fun `Empty list returns null`() {
        Assertions.assertNull(deleteDuplicates(null))
    }

    @Test
    fun `Repeated element returns a single list node`() {
        val ln0 = ListNode(5)
        val ln1 = ListNode(5)
        ln0.next = ln1
        val res = deleteDuplicates(ln0)
        Assertions.assertNotNull(res)
    }

    @Test
    fun `Removes repeats in the listed list`() {
        val ln0 = ListNode(5)
        val ln1 = ListNode(6)
        val ln2 = ListNode(6)
        ln0.next = ln1
        ln1.next = ln2
        val res = deleteDuplicates(ln0)
        Assertions.assertNotNull(res)
    }

    @Test
    fun `test0`() {
        val ln0 = ListNode(1)
        val ln1 = ListNode(2)
        val ln2 = ListNode(3)
        val ln3 = ListNode(3)
        val ln4 = ListNode(4)
        val ln5 = ListNode(4)
        val ln6 = ListNode(5)
        ln0.next = ln1
        ln1.next = ln2
        ln2.next = ln3
        ln3.next = ln4
        ln4.next = ln5
        ln5.next = ln6
        val res = deleteDuplicates(ln0)
        Assertions.assertNotNull(res)
    }
}