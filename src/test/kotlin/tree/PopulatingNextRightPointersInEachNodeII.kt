package tree/*
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.
 */
class PopulatingNextRightPointersInEachNodeII {
    fun connect(root: Node?): Node? {
        val memo = mutableMapOf<Int, Node>()
        inOrder(root, 0, memo)
        return root
    }

    private fun inOrder(node: Node?, depth: Int, memo: MutableMap<Int, Node>) {
        if (node == null) {
            return
        }
        inOrder(node.left, depth + 1, memo)
        memo[depth]?.next = node
        memo[depth] = node
        inOrder(node.right, depth + 1, memo)
    }
}